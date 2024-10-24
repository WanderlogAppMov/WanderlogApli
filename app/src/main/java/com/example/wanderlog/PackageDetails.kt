package com.example.wanderlog

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class PackageDetails : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
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

        // Recuperar los datos del Intent
        val destination = intent.getStringExtra("destination")
        val price = intent.getDoubleExtra("pricePerStudent", 0.0)
        val hotelName = intent.getStringExtra("hotelName")
        val flightDetails = intent.getStringExtra("flightAirline")
        val restaurantName = intent.getStringExtra("restaurantName")
        val attractionName = intent.getStringExtra("attractionName")

        // Actualizar las vistas con los datos
        findViewById<TextView>(R.id.packageTitle).text = destination
        findViewById<TextView>(R.id.packagePrice).text = "$$price per traveler"
        findViewById<TextView>(R.id.hotelName).text = hotelName
        findViewById<TextView>(R.id.flightDetails).text = flightDetails
        findViewById<TextView>(R.id.restaurantName).text = restaurantName
        findViewById<TextView>(R.id.attractionName).text = attractionName
    }
}
