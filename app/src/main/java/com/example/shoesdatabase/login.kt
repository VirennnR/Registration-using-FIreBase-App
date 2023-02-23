package com.example.shoesdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class login : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val signButton = findViewById<Button>(R.id.btnSignUp)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etMail = findViewById<TextInputEditText>(R.id.etMail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val etSize = findViewById<TextInputEditText>(R.id.etSize)
        val etId = findViewById<TextInputEditText>(R.id.etId)

        signButton.setOnClickListener{

            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val password = etPassword.text.toString()
            val size = etSize.text.toString()
            val id = etId.text.toString()

            val user = User(name, mail, password, size, id)
            database = FirebaseDatabase.getInstance().getReference("Users")

            database.child(id).setValue(user).addOnSuccessListener {

                etName.text?.clear()
                etMail.text?.clear()
                etPassword.text?.clear()
                etSize.text?.clear()
                etId.text?.clear()

                Toast.makeText(this,"User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{

                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
            }


        }

        val signInText = findViewById<TextView>(R.id.tvSignIn)
        signInText.setOnClickListener{

            val openSignIN = Intent(this,SignIn::class.java)
            startActivity(openSignIN)
        }
    }
}