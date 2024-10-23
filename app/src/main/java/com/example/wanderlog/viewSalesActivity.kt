package com.example.wanderlog

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class viewSalesActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_sales)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ivGoBackAdminPanel = findViewById<ImageView>(R.id.imageView)
        ivGoBackAdminPanel.setOnClickListener {
            finish()
        }
        val ivGoActivityManagePackages = findViewById<ImageView>(R.id.ivViewPackages)
        ivGoActivityManagePackages.setOnClickListener {
            val intent = Intent(this, ManagePackagesActivity::class.java)
            startActivity(intent)
        }
    }
}