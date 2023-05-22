package com.example.rsspmarket

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

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
        val intent:Intent
        if (isConnected==false)
             intent=  Intent(this,Authentification::class.java)
        else
            intent=  Intent(this,Produits::class.java)


        // Créer un Handler pour le thread principal

        val monThread=object:Thread() {
            override fun run() {
                super.run()
                Looper.prepare()


                while (true) {
                    object : CountDownTimer(1000L, 1000L) {
                        override fun onTick(millisUntilFinished: Long) {

                        }

                        override fun onFinish() {

                            Toast.makeText(applicationContext, "TRRRRRRRRRRRR", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }.start()
                }
            }
        }
        monThread.start()
            // Effectuer le travail en arrière-plan ici











            // Envoyer un message au thread principal








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