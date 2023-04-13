package com.example.rsspmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class authntification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authntification_activity)
    }

    fun partager(view: View) {
        val message = "Je partage avec vous la nouvelle application de RSSP Market : WWW.ENSA.AC.MA"
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type ="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, message)
        startActivity(Intent.createChooser(shareIntent, "Partager via"))

    }

}