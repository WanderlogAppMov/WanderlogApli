package com.example.wanderlog

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar el toolbar
        val toolbar = findViewById<Toolbar>(R.id.myToolbar)
        setSupportActionBar(toolbar)

        // Remover el título del Toolbar si no es necesario
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Encontrar el botón por su ID
        val button = findViewById<ImageButton>(R.id.ibPackages)

        // Configurar el click listener del botón
        button.setOnClickListener {
            // Crear un Intent para ir a la segunda actividad
            val intent = Intent(this, Search::class.java)
            startActivity(intent) // Iniciar la segunda actividad
        }
    }
}