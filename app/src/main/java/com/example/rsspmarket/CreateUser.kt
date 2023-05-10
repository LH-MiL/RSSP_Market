package com.example.rsspmarket

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream

class CreateUser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_user_activity)
    }

    fun createNewUser(view: View) {

        val email=findViewById<EditText>(R.id.email)
        val password=findViewById<EditText>(R.id.password)
        val confirmedPassword=findViewById<EditText>(R.id.confirmedPassword)
        val NomPrenom=findViewById<EditText>(R.id.NomPrenom)
        val ville=findViewById<Spinner>(R.id.ville)
        val user=User(NomPrenom.text.toString(),ville.selectedItem.toString())
        if(validerUser(email,password,confirmedPassword)){
            val auth=FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(email.text.toString(),password.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        var dbReference=FirebaseDatabase.getInstance().getReference("Utilisateurs")
                        dbReference.child(auth.currentUser?.uid.toString()).setValue(user)
                        startActivity(Intent(this, Produit::class.java))
                    }
                    else
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                }

        }

    }
    fun validerUser(email:EditText,password:EditText,confirmedPassword:EditText):Boolean{
        var validation=true
        if(!Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
            validation = false
            email.error = "Le format de l'email n'est pas valide"
        }

        if(password.text.toString()!=confirmedPassword.text.toString()) {
            validation = false
            confirmedPassword.error = "Confirmation de mot de passe différent du mot de passe "
        }
        if(password.length()<6){
            validation=false
            password.error="Le mot de passe doit contenir au moins 6 caractères"
        }
        return validation
    }

    fun Camera(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 2)

    }
    fun Parcourir(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, 1)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) { val imageUri = data.data  }
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            val imageBitmap=data?.extras?.get("data") as Bitmap
            val outputStream = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            val path = MediaStore.Images.Media.insertImage(contentResolver, imageBitmap, "Titre de l'image", null)
            val imageUri: Uri = Uri.parse(path)

        }
    }

}