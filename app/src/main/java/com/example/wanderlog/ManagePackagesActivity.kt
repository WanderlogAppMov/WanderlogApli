package com.example.wanderlog

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ManagePackagesActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_manage_packages)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ivGoBackAdminPanel = findViewById<ImageView>(R.id.imageView)
        ivGoBackAdminPanel.setOnClickListener {
            finish()
        }
        val ivGoViewSales = findViewById<ImageView>(R.id.ivViewSales)
        ivGoViewSales.setOnClickListener {
            val intent = Intent(this, viewSalesActivity::class.java)
            startActivity(intent)
        }
        val ivEditPackage1 = findViewById<ImageView>(R.id.editPackage1)
        ivEditPackage1.setOnClickListener {
            val intent = Intent(this, managePackageActivity::class.java)
            startActivity(intent)
        }
        val ivEditPackage2 = findViewById<ImageView>(R.id.editPackage2)
        ivEditPackage2.setOnClickListener {
            val intent = Intent(this, managePackageActivity::class.java)
            startActivity(intent)
        }
        val ivEditPackage3 = findViewById<ImageView>(R.id.editPackage3)
        ivEditPackage3.setOnClickListener {
            val intent = Intent(this, managePackageActivity::class.java)
            startActivity(intent)
        }
    }
}