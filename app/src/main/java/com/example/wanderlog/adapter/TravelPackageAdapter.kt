package com.example.wanderlog.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wanderlog.PackageDetails
import com.example.wanderlog.R
import com.example.wanderlog.data.model.TravelPackage

class TravelPackageAdapter(
    private var travelPackages: MutableList<TravelPackage>,
    private val onFavoriteClicked: (TravelPackage) -> Unit  // Callback para manejar clic en favorito
) : RecyclerView.Adapter<TravelPackageAdapter.ViewHolder>() {

    // ViewHolder con el botón de favorito añadido
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val subtitle: TextView = itemView.findViewById(R.id.subtitle)
        val price: TextView = itemView.findViewById(R.id.price)
        val image: ImageView = itemView.findViewById(R.id.cardImage)
        val detailsButton: TextView = itemView.findViewById(R.id.detailsButton)
        val favoriteButton: ImageButton = itemView.findViewById(R.id.favoriteButton)  // Botón de favorito
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_travel_package, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val travelPackage = travelPackages[position]
        holder.title.text = travelPackage.destination
        holder.subtitle.text = "${travelPackage.continent}, ${travelPackage.agency.organizationName}"
        holder.price.text = "$${travelPackage.pricePerStudent} per traveler"
        holder.image.setImageResource(R.drawable.cuscoofi)  // Cambia si quieres cargar imágenes dinámicamente

        // Manejar clic en el botón de "detalles"
        holder.detailsButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, PackageDetails::class.java)
            // Pasar los detalles del paquete
            intent.putExtra("destination", travelPackage.destination)
            intent.putExtra("hotelName", travelPackage.hotel.hotelName)
            intent.putExtra("hotelCity", travelPackage.hotel.city)
            intent.putExtra("hotelStars", travelPackage.hotel.stars)
            intent.putExtra("flightAirline", travelPackage.flight.airline)
            intent.putExtra("restaurantName", travelPackage.restaurant.restaurantName)
            intent.putExtra("attractionName", travelPackage.attraction.attractionName)
            intent.putExtra("pricePerStudent", travelPackage.pricePerStudent)
            holder.itemView.context.startActivity(intent)
        }

        // Manejar clic en el botón de "favorito"
        holder.favoriteButton.setOnClickListener {
            onFavoriteClicked(travelPackage)
            holder.favoriteButton.setImageResource(R.drawable.heart_filled)  // Cambiar el icono al marcar como favorito
        }
    }

    override fun getItemCount(): Int {
        return travelPackages.size
    }

    // Método para actualizar la lista de paquetes
    fun updatePackages(newPackages: List<TravelPackage>) {
        travelPackages.clear()
        travelPackages.addAll(newPackages)
        notifyDataSetChanged()
    }
}
