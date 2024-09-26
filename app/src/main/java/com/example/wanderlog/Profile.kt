package com.example.wanderlog

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var genderEditText: EditText
    private lateinit var birthdateEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        firstNameEditText = findViewById(R.id.edit_first_name)
        lastNameEditText = findViewById(R.id.edit_last_name)
        genderEditText = findViewById(R.id.edit_gender)
        birthdateEditText = findViewById(R.id.edit_birthdate)
        emailEditText = findViewById(R.id.edit_email)
        passwordEditText = findViewById(R.id.edit_password)

        val footerButton1 = findViewById<ImageButton>(R.id.footerButton1)
        footerButton1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val footerButton2 = findViewById<ImageButton>(R.id.footerButton2)
        footerButton2.setOnClickListener {
            val intent = Intent(this, Favorites::class.java)
            startActivity(intent)
        }
    }
}
