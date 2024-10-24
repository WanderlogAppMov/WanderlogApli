package com.example.wanderlog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Search : AppCompatActivity() {
    private val PREFS_NAME = "SearchPrefs"
    private val KEY_DESTINATIONS = "destinations"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        val toolbarButton = findViewById<ImageButton>(R.id.toolbarButton)
        val btSearch = findViewById<Button>(R.id.btSearch)
        val etDestination = findViewById<AutoCompleteTextView>(R.id.ptDestination)
        val etMinPrice = findViewById<EditText>(R.id.ptMinprice)
        val etMaxPrice = findViewById<EditText>(R.id.ptMaxprice)
        val spOrder = findViewById<Spinner>(R.id.spOrder)

        ArrayAdapter.createFromResource(
            this,
            R.array.order_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spOrder.adapter = adapter
        }

        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedDestinations = sharedPreferences.getStringSet(KEY_DESTINATIONS, setOf())?.toList() ?: listOf()
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, savedDestinations)
        etDestination.setAdapter(adapter)

        btSearch.setOnClickListener {
            val destination = etDestination.text.toString()
            val minPrice = etMinPrice.text.toString().toDoubleOrNull()
            val maxPrice = etMaxPrice.text.toString().toDoubleOrNull()
            val order = spOrder.selectedItem.toString()

            val destinations = sharedPreferences.getStringSet(KEY_DESTINATIONS, mutableSetOf())?.toMutableSet()
            if (destination.isNotEmpty()) {
                destinations?.add(destination)
                sharedPreferences.edit().putStringSet(KEY_DESTINATIONS, destinations).apply()
            }

            val intent = Intent(this, Results::class.java).apply {
                putExtra("destination", destination)
                putExtra("minPrice", minPrice)
                putExtra("maxPrice", maxPrice)
                putExtra("order", order)
            }
            startActivity(intent)
        }

        toolbarButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}