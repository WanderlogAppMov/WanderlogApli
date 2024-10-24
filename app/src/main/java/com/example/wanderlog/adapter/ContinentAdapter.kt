package com.example.wanderlog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.wanderlog.R
import com.example.wanderlog.data.model.Hotels

class ContinentAdapter(private val continentData: Map<String, List<Hotels>>) :
    RecyclerView.Adapter<ContinentAdapter.ContinentViewHolder>() {

    class ContinentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val continentName: TextView = itemView.findViewById(R.id.continentName)
        val hotelsRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerViewHotels)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContinentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_continent, parent, false)
        return ContinentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContinentViewHolder, position: Int) {
        val continentName = continentData.keys.elementAt(position)
        val hotels = continentData[continentName]

        holder.continentName.text = continentName

        // Configurar el RecyclerView de hoteles para que sea horizontal
        val layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        holder.hotelsRecyclerView.layoutManager = layoutManager
        holder.hotelsRecyclerView.adapter = HotelAdapter(hotels!!)
    }

    override fun getItemCount(): Int {
        return continentData.size
    }
}
