package com.example.rsspmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth

class Produit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.produit_activity)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_produits,menu)
        return true   }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.seDeconnecter) {
        val auth=FirebaseAuth.getInstance()
            auth.signOut()
        startActivity(Intent(this,Authentification::class.java))
        }
        if (item.itemId == R.id.monChariot) {
            startActivity(Intent(this,MonChariot::class.java))
        }
        return true }



}