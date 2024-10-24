package com.example.wanderlog

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanderlog.adapter.TravelPackageAdapter
import com.example.wanderlog.data.model.TravelPackage
import com.example.wanderlog.data.network.ApiClient
import com.example.wanderlog.data.network.TravelPackageService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Results : AppCompatActivity() {

    private lateinit var travelPackageService: TravelPackageService
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TravelPackageAdapter
    private var travelPackages = mutableListOf<com.example.wanderlog.data.model.TravelPackage>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        val toolbarButton = findViewById<ImageButton>(R.id.toolbarButton)

        toolbarButton.setOnClickListener {
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
        }

        // Configurar Retrofit
        travelPackageService = ApiClient.retrofit.create(TravelPackageService::class.java)

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializar el adaptador
        adapter = TravelPackageAdapter(travelPackages)
        recyclerView.adapter = adapter

        // Llamar a la API para obtener los paquetes de viaje
        getTravelPackages()
    }

    private fun getTravelPackages() {
        travelPackageService.getAllTravelPackages().enqueue(object : Callback<List<TravelPackage>> {
            override fun onResponse(call: Call<List<TravelPackage>>, response: Response<List<TravelPackage>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        travelPackages.clear()
                        travelPackages.addAll(it)
                        adapter.notifyDataSetChanged()  // Actualizar el adaptador con los nuevos datos
                    }
                }
            }

            override fun onFailure(call: Call<List<TravelPackage>>, t: Throwable) {
                // Manejar error
            }
        })
    }
}