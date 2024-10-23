package com.example.wanderlog

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class adminPanelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_admin_panel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ivGoActivityManagePackages = findViewById<ImageView>(R.id.imageView15)
        ivGoActivityManagePackages.setOnClickListener {
            val intent = Intent(this, ManagePackagesActivity::class.java)
            startActivity(intent)
        }

        val ivGoViewSales = findViewById<ImageView>(R.id.imageView16)
        ivGoViewSales.setOnClickListener {
            val intent = Intent(this, viewSalesActivity::class.java)
            startActivity(intent)
        }

        val ivGoBackMainActivity = findViewById<ImageView>(R.id.imageView)
        ivGoBackMainActivity.setOnClickListener {
            finish()
        }
    }
}