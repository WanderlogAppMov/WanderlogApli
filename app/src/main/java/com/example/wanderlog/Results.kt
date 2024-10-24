package com.example.wanderlog

import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
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
    private var travelPackages = mutableListOf<TravelPackage>()
    private var favoritePackages = mutableListOf<TravelPackage>() // Lista de favoritos

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

        // Inicializar el adaptador con la lógica de favoritos
        adapter = TravelPackageAdapter(travelPackages, onFavoriteClicked = { travelPackage ->
            handleFavoriteClick(travelPackage)
        })
        recyclerView.adapter = adapter

        val destination = intent.getStringExtra("destination")
        val minPrice = intent.getDoubleExtra("minPrice", Double.MIN_VALUE)
        val maxPrice = intent.getDoubleExtra("maxPrice", Double.MAX_VALUE)
        val order = intent.getStringExtra("order")
         // Llamar a la API para obtener los paquetes de viaje
        getTravelPackages(destination, minPrice, maxPrice, order)

        // Botón de ordenación
        val buttonSort = findViewById<Button>(R.id.buttonSort)
        buttonSort.setOnClickListener { showSortMenu(it) }
    }

    private fun getTravelPackages(destination: String?, minPrice: Double, maxPrice: Double, order: String?) {
        travelPackageService.getAllTravelPackages().enqueue(object : Callback<List<TravelPackage>> {
            override fun onResponse(call: Call<List<TravelPackage>>, response: Response<List<TravelPackage>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        travelPackages.clear()
                        travelPackages.addAll(it.filter { travelPackage ->
                            val matchesDestination = destination?.let { dest ->
                                travelPackage.destination.contains(dest, ignoreCase = true)
                            } ?: true
                            val matchesPrice = travelPackage.pricePerStudent in minPrice..maxPrice
                            matchesDestination && matchesPrice
                        })
                        if (order == "Ascending") {
                            travelPackages.sortBy { it.pricePerStudent }
                        } else if (order == "Descending") {
                            travelPackages.sortByDescending { it.pricePerStudent }
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<TravelPackage>>, t: Throwable) {
                // Manejar error
            }
        })
    }

    // Función para manejar clic en favorito
    private fun handleFavoriteClick(travelPackage: TravelPackage) {
        if (FavoriteManager.isFavorite(travelPackage)) {
            FavoriteManager.removePackage(travelPackage)
            Toast.makeText(this, "${travelPackage.destination} removido de favoritos", Toast.LENGTH_SHORT).show()
        } else {
            FavoriteManager.addPackage(travelPackage)
            Toast.makeText(this, "${travelPackage.destination} añadido a favoritos", Toast.LENGTH_SHORT).show()
        }

        // Cambiar el estado del ícono de favorito en el adaptador
        adapter.notifyDataSetChanged()
    }


    private fun showSortMenu(view: View) {
        val popup = PopupMenu(this, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.sort_menu, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.sortByPrice -> {
                    // Lógica para ordenar por precio
                    travelPackages.sortBy { it.pricePerStudent }
                    adapter.notifyDataSetChanged()
                    true
                }
                R.id.sortByAlphabet -> {
                    // Lógica para ordenar alfabéticamente
                    travelPackages.sortBy { it.destination }
                    adapter.notifyDataSetChanged()
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
}
