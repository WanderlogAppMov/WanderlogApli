package com.example.wanderlog

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val travelPackage1 = TravelPackage(
            title = "Cusco's adventure",
            subtitle = "Cusco, Perú",
            details = "5 days\nLATAM Airlines\nHotel \"Palacio del Inka\"",
            price = "$350 per traveler",
            imageResId = R.drawable.cuscoofi
        )

        val travelPackage2 = TravelPackage(
            title = "Discover Arequipa",
            subtitle = "Arequipa, Perú",
            details = "4 days\n Sky Airlines\nCasa Andina",
            price = "$300 per traveler",
            imageResId = R.drawable.cuscoofi
        )

        val favoriteIcon1 = findViewById<ImageView>(R.id.favoriteIcon)
        favoriteIcon1.setOnClickListener {
            toggleFavorite(travelPackage1, favoriteIcon1)
        }

        val favoriteIcon2 = findViewById<ImageView>(R.id.favoriteIcon2)
        favoriteIcon2.setOnClickListener {
            toggleFavorite(travelPackage2, favoriteIcon2)
        }

        val toolbarButton = findViewById<ImageButton>(R.id.toolbarButton)
        toolbarButton.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
        }

        val detailsButton = findViewById<Button>(R.id.detailsButton)
        detailsButton.setOnClickListener {
            val intent = Intent(this, PackageDetails::class.java)
            startActivity(intent)
        }
    }

    private fun toggleFavorite(travelPackage: TravelPackage, favoriteIcon: ImageView) {
        if (FavoriteManager.getFavoritePackages().contains(travelPackage)) {
            FavoriteManager.removePackage(travelPackage)
            favoriteIcon.setImageResource(R.drawable.heart) // Icono no favorito
        } else {
            FavoriteManager.addPackage(travelPackage)
            favoriteIcon.setImageResource(R.drawable.heart_filled) // Icono favorito
        }
    }
}