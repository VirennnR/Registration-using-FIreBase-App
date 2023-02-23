package com.example.shoesdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val name = intent.getStringExtra(SignIn.KEY2)
        val mail = intent.getStringExtra(SignIn.KEY1)
        val size = intent.getStringExtra(SignIn.KEY3)
        val id = intent.getStringExtra(SignIn.KEY4)

        val welcomeText = findViewById<TextView>(R.id.tvWelcome)
        val mailText = findViewById<TextView>(R.id.tvMail)
        val sizeText = findViewById<TextView>(R.id.tvSize)
        val idText = findViewById<TextView>(R.id.tvUniqueId)

        welcomeText.text = "Welcome $name"

        mailText.text = "Mail : $mail"
        sizeText.text = "Size : $size"
        idText.text = "UserID : $id"

    }
}