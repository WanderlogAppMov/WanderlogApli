package com.example.wanderlog

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.wanderlog.data.model.TravelPackage

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
                // Asegúrate de que travelPackage es del tipo correcto
                val packageView = layoutInflater.inflate(R.layout.package_item, favoritesContainer, false)

                // Verificación de las propiedades
                travelPackage?.let {
                    val titleView = packageView.findViewById<TextView>(R.id.title)
                    titleView.text = it.destination

                    val subtitleView = packageView.findViewById<TextView>(R.id.subtitle)
                    subtitleView.text = "${it.continent}, ${it.agency.organizationName}"

                    val priceView = packageView.findViewById<TextView>(R.id.price)
                    priceView.text = "$${it.pricePerStudent}"

                    val favoriteIcon = packageView.findViewById<ImageView>(R.id.favoriteIcon)
                    favoriteIcon.setImageResource(R.drawable.heart_filled)  // Icono de favorito lleno
                    favoriteIcon.setOnClickListener {
                        // Aquí pasamos el travelPackage en lugar del View (it)
                        FavoriteManager.removePackage(travelPackage)
                        favoritesContainer.removeView(packageView)

                        // Verificamos si la lista de favoritos está vacía
                        if (FavoriteManager.getFavoritePackages().isEmpty()) {
                            favoritesContainer.visibility = LinearLayout.GONE
                            emptyStateContainer.visibility = LinearLayout.VISIBLE
                        }
                    }


                    favoritesContainer.addView(packageView)
                }
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

