package com.example.wanderlog

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val imageView12: ImageView = findViewById(R.id.imageView12)
        imageView12.setOnClickListener {
            val intent = Intent(this, HotelsEditorActivity::class.java)
            startActivity(intent)
        }
        val imageView13: ImageView = findViewById(R.id.imageView13)
        imageView13.setOnClickListener {
            val intent = Intent(this, FlightsEditorActivity::class.java)
            startActivity(intent)
        }
        val imageView14: ImageView = findViewById(R.id.imageView14)
        imageView14.setOnClickListener {
            val intent = Intent(this, RestaurantsEditorActivity::class.java)
            startActivity(intent)
        }
        val imageView10: ImageView = findViewById(R.id.imageView10)
        imageView10.setOnClickListener {
            val intent = Intent(this, AttractionsEditorActivity::class.java)
            startActivity(intent)
        }
        val imageView3: ImageView = findViewById(R.id.imageView3)
        imageView3.setOnClickListener {
            val intent = Intent(this, BookingCommunicationActivity::class.java)
            startActivity(intent)
        }
        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.setOnClickListener {
            val intent = Intent(this, userProfileView::class.java)
            startActivity(intent)
        }
        val imageView2: ImageView = findViewById(R.id.imageView2)
        imageView2.setOnClickListener {
            val intent = Intent(this, adminPanelActivity::class.java)
            startActivity(intent)
        }
        val button3: Button = findViewById(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this, PackagesEditorActivity::class.java)
            startActivity(intent)
        }
        val button4: Button = findViewById(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(this, adminPanelActivity::class.java)
            startActivity(intent)
        }
    }
}