package com.example.rsspmarket

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class CreateUser : AppCompatActivity() {
    lateinit var uriImageUser:Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_user_activity)
        uriImageUser=Uri.parse("android.resource://" + packageName + "/" + R.drawable.icon_user)
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
                        startActivity(Intent(this, Produits::class.java))

                        //Ajouter l'image
                        //Création/Récupération d'une instance de FirbaseStorage Reference (créer ou récupérer un fichier)

                        var storageReference= FirebaseStorage.getInstance().getReference(auth.currentUser?.uid.toString())
                        //Ecrire dans la base de données (Upload un fichier) exemple : une image dans les ressources
                        storageReference.
                        putFile(uriImageUser)





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


        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 2)
        } else {
            // La permission n'est pas encore accordée, on va la redemander à nouveau
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 1)
        }






    }
    fun Parcourir(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, 1)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) { uriImageUser= data.data!!
        }
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            val imageBitmap=data?.extras?.get("data") as Bitmap
            val outputStream = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            val path = MediaStore.Images.Media.insertImage(contentResolver, imageBitmap, "Titre de l'image", null)
            uriImageUser = Uri.parse(path)
        }
        findViewById<ImageView>(R.id.imageUser).setImageURI(uriImageUser)
    }
        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
            when (requestCode) {
                1 -> {
                    if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        // Vous pouvez exécuter le code qui nécessite cette permission
                        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(intent, 2)
                    } else {
                        // La permission a été refusée
                        Toast.makeText(this,"Vous devez nous donner l'accord pour utiliser votre caméra",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

}