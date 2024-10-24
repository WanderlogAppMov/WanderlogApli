package com.example.wanderlog

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wanderlog.adapter.ContinentAdapter
import com.example.wanderlog.adapter.HotelAdapter
import com.example.wanderlog.data.model.Hotels
import com.example.wanderlog.data.network.ApiClient
import com.example.wanderlog.data.network.HotelService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotelsEditorActivity : AppCompatActivity() {

    private lateinit var hotelService: HotelService
    private lateinit var recyclerViewContinents: RecyclerView
    private lateinit var continentAdapter: ContinentAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hotels_editor)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializa el RecyclerView principal que contiene las listas de continentes
        recyclerViewContinents = findViewById(R.id.recyclerViewContinents)
        recyclerViewContinents.layoutManager = LinearLayoutManager(this)

        // Inicializa Retrofit para llamar al servicio de hoteles
        hotelService = ApiClient.retrofit.create(HotelService::class.java)

        // Llamar a la API para obtener los hoteles
        getHotelsFromApi()

        val ivGoBackMainActivity = findViewById<ImageView>(R.id.imageView)
        ivGoBackMainActivity.setOnClickListener {
            finish()
        }

        //val btAddToPlan = findViewById<Button>(R.id.button5)
        //btAddToPlan.setOnClickListener {
        //    finish()
        //}
    }

    private fun getHotelsFromApi() {
        hotelService.getAllHotels().enqueue(object : Callback<List<Hotels>> {
            override fun onResponse(call: Call<List<Hotels>>, response: Response<List<Hotels>>) {
                if (response.isSuccessful) {
                    val hotels = response.body()
                    hotels?.let {
                        // Agrupa los hoteles por continente
                        val groupedHotels = it.groupBy { hotel -> hotel.continent.continentName }
                        updateRecyclerView(groupedHotels)
                    }
                } else {
                    Toast.makeText(this@HotelsEditorActivity, "Error al obtener los hoteles", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Hotels>>, t: Throwable) {
                Toast.makeText(this@HotelsEditorActivity, "Fallo en la conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateRecyclerView(groupedHotels: Map<String, List<Hotels>>) {
        // Pasar los hoteles agrupados al adaptador de continentes
        continentAdapter = ContinentAdapter(groupedHotels)
        recyclerViewContinents.adapter = continentAdapter
    }
}
