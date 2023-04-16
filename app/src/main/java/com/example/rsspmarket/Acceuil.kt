package com.example.rsspmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.TextView

class Acceuil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acceuil_activity)
        val chrono=findViewById<TextView>(R.id.chargement)
        val progress=findViewById<ProgressBar>(R.id.progressChargement)
        val intent=Intent(this,Authentification::class.java)
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