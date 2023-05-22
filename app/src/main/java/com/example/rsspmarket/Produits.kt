package com.example.rsspmarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class Produits : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.produit_activity)
        val listView=findViewById<ListView>(R.id.listViewProduit)
        val maListe=arrayListOf<Produit>()
        for (i in 1..20)
            maListe.add(Produit(i,"Déscription de "+i.toString(),R.drawable.chariot))

        listView.adapter= AdapterProduit(this,maListe)
        for (i in 40..60)
            maListe.add(Produit(i,"Déscription de "+i.toString(),R.drawable.chariot))
        //(listView.adapter as ArrayAdapter<Produit>).notifyDataSetChanged()
        listView.adapter= AdapterProduit(this,maListe)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_produits,menu)
        return true   }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.seDeconnecter) {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Déconnexion")
            builder.setMessage("Voulez vous vraiment quitter?")
            builder.setPositiveButton("Oui") { dialog, which ->
                val auth=FirebaseAuth.getInstance()
                auth.signOut()
                startActivity(Intent(this,Authentification::class.java))
            }
            builder.setNegativeButton("Non") { dialog, which ->
                // rien à faire
            }
            builder.show()

        }
        if (item.itemId == R.id.monChariot) {
            startActivity(Intent(this,MonChariot::class.java))
        }
        return true }



}