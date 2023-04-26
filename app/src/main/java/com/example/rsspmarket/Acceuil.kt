package com.example.rsspmarket

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.TextView

class Acceuil : AppCompatActivity() {
    override fun onDestroy() {
        super.onDestroy()
        MediaPlayer.create(this, R.raw.open_door).start()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acceuil_activity)
        supportActionBar?.hide()
        val chrono=findViewById<TextView>(R.id.chargement)
        val progress=findViewById<ProgressBar>(R.id.progressChargement)
        val fichierShared = getSharedPreferences("RSSP", Context.MODE_PRIVATE)
        val isConnected = fichierShared.getBoolean("estConnecte", false)

        val intent= if (isConnected==false) Intent(this,Authentification::class.java)
                    else Intent(this,Produit::class.java)





        object: CountDownTimer(2000L,20L){
            override fun onTick(millisUntilFinished: Long) {
                val i=((2000-millisUntilFinished)/20).toInt()
                chrono.text="Chargement : "+i.toString() + "%"
                progress.setProgress(i)
            }
            override fun onFinish() {

                startActivity(intent)
                finish()
            }
        }.start()

    }

}