package com.example.wanderlog

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class PackageDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_packagedetails)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        val toolbarButton = findViewById<ImageButton>(R.id.toolbarButton)

        toolbarButton.setOnClickListener {
            val intent = Intent(this, Results::class.java)
            startActivity(intent)
        }
    }
}
