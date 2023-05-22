package com.example.rsspmarket

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible

class AdapterProduit(var context: Activity, var listProduit:ArrayList<Produit>): ArrayAdapter<Produit>(context,R.layout.item_produits,listProduit){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater= LayoutInflater.from(context)
        val view=inflater.inflate(R.layout.item_produits,null)
        val image=view.findViewById<ImageView>(R.id.imageProfuit)
        val prix=view.findViewById<TextView>(R.id.prixProduit)
        val description=view.findViewById<TextView>(R.id.descriptionProduit)
        val addProduit=view.findViewById<Button>(R.id.addProduit)
        val share=view.findViewById<ImageView>(R.id.shareProduit)
        prix.isVisible=false
        description.isVisible=false
        addProduit.isVisible=false
        share.isVisible=false


        image.setImageResource(listProduit[position].image)
        prix.text=listProduit[position].prix.toString()
        description.text=listProduit[position].description

        share.setOnClickListener(){
            listProduit.removeAt(position)
            this.notifyDataSetChanged()
        }



        image.setOnClickListener(){
            prix.isVisible=true
            description.isVisible=true
            addProduit.isVisible=true
            share.isVisible=true
        }

        return view     }   }
