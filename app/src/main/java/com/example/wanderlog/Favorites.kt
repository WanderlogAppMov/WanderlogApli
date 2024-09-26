package com.example.wanderlog

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Favorites : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        val favoritesContainer = findViewById<LinearLayout>(R.id.favoritesContainer)
        val emptyStateContainer = findViewById<LinearLayout>(R.id.emptyStateContainer)
        val favoritePackages = FavoriteManager.getFavoritePackages()

        if (favoritePackages.isEmpty()) {
            favoritesContainer.visibility = LinearLayout.GONE
            emptyStateContainer.visibility = LinearLayout.VISIBLE
        } else {
            emptyStateContainer.visibility = LinearLayout.GONE
            favoritePackages.forEach { travelPackage ->
                val packageView = layoutInflater.inflate(R.layout.package_item, favoritesContainer, false)

                val titleView = packageView.findViewById<TextView>(R.id.title)
                titleView.text = travelPackage.title

                val subtitleView = packageView.findViewById<TextView>(R.id.subtitle)
                subtitleView.text = travelPackage.subtitle

                val detailsView = packageView.findViewById<TextView>(R.id.details)
                detailsView.text = travelPackage.details

                val priceView = packageView.findViewById<TextView>(R.id.price)
                priceView.text = travelPackage.price

                val favoriteIcon = packageView.findViewById<ImageView>(R.id.favoriteIcon)
                favoriteIcon.setImageResource(R.drawable.heart_filled) // Icono favorito
                favoriteIcon.setOnClickListener {
                    FavoriteManager.removePackage(travelPackage)
                    favoritesContainer.removeView(packageView)
                }

                favoritesContainer.addView(packageView)
            }
        }

        val footerButton1 = findViewById<ImageButton>(R.id.footerButton1)
        footerButton1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val footerButton3 = findViewById<ImageButton>(R.id.footerButton3)
        footerButton3.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }
}