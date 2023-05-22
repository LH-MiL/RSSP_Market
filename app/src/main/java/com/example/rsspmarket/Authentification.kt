package com.example.rsspmarket

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Authentification : AppCompatActivity() {
lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authentification_activity)

        val youtube=findViewById<ImageView>(R.id.youtube)
        youtube.setOnClickListener(){
            val uri= Uri.parse("http://www.youtube.com")
            val intent=Intent(Intent.ACTION_VIEW,uri)
            startActivity(intent)
        }
       val seConnecter=findViewById<Button>(R.id.seConnecter)
        seConnecter.setOnClickListener(){
            val email=findViewById<EditText>(R.id.email).text.toString()
            val password=findViewById<EditText>(R.id.password).text.toString()
            auth=FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        val sharedPref = getSharedPreferences("RSSP", Context.MODE_PRIVATE)
                        val editeur = sharedPref.edit()
                        editeur.putBoolean("estConnecte",true)
                        editeur.apply()
                        val intent=Intent(this,Produits::class.java)
                        startActivity(intent)

                    }
                    else Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                    (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(1000)
                }



        }

    }


    fun partager(view: View) {
        val message = "Je partage avec vous la nouvelle application de RSSP Market : WWW.ENSA.AC.MA"
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type ="text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, message)
        startActivity(Intent.createChooser(shareIntent, "Partager via"))
    }

    fun webOpen(view: View) {
        val uri= Uri.parse("http://www.ensa.ac.ma")
        val intent=Intent(Intent.ACTION_VIEW,uri)
        startActivity(intent)
    }

    fun maps(view: View) {
        val latitude = 31.645955
        val longitude = -8.0200795
        val label = "ENSA de Marrakech"
        val uri = Uri.parse("geo:$latitude,$longitude?q=$label")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)

    }

    fun appel(view: View) {
        val uri= Uri.parse("tel:0656565656")
        val intent=Intent(Intent.ACTION_DIAL,uri)
        startActivity(intent)

    }

    fun creationUtilisateur(view: View) {
        startActivity(Intent(this,CreateUser::class.java))
    }
}