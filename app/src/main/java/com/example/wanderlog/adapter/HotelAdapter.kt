package com.example.wanderlog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wanderlog.R
import com.example.wanderlog.data.model.Hotels

class HotelAdapter(private val hotels: List<Hotels>) : RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    class HotelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelName: TextView = itemView.findViewById(R.id.hotelName)
        val hotelImage: ImageView = itemView.findViewById(R.id.hotelImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hotel, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hotels[position]
        holder.hotelName.text = hotel.hotelName
        // Puedes usar una librería como Glide o Picasso para cargar imágenes desde URLs
        holder.hotelImage.setImageResource(R.drawable.arequipa) // Imagen por defecto o desde una URL
    }

    override fun getItemCount(): Int {
        return hotels.size
    }
}