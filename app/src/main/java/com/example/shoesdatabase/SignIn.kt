package com.example.shoesdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    lateinit var databaseReference : DatabaseReference

    companion object{
        const val KEY1 = "com.example.shoesdatabase.SignIn.mail"
        const val KEY2 = "com.example.shoesdatabase.SignIn.name"
        const val KEY3 = "com.example.shoesdatabase.SignIn.size"
        const val KEY4 = "com.example.shoesdatabase.SignIn.id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInButton = findViewById<Button>(R.id.btnSignIn)
        val userName = findViewById<TextInputEditText>(R.id.userNameEditText)

        signInButton.setOnClickListener{

            val uniqueId = userName.text.toString()
            if(uniqueId.isNotEmpty()){

                readData(uniqueId)
            }else{

                Toast.makeText(this,"Please enter a username", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun readData(uniqueId : String){

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(uniqueId).get().addOnSuccessListener {

            if(it.exists()){

                val mail = it.child("mail").value
                val name = it.child( "name").value
                val size = it.child("size").value
                val id = it.child("id").value

                val intentWelcome = Intent(this,Welcome::class.java)
                intentWelcome.putExtra(KEY1, mail.toString())
                intentWelcome.putExtra(KEY2, name.toString())
                intentWelcome.putExtra(KEY3, size.toString())
                intentWelcome.putExtra(KEY4, id.toString())
                startActivity(intentWelcome)

            }else{

                Toast.makeText(this,"User doesn't exist", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener{
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()

        }
    }
}