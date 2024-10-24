package com.example.wanderlog.data.model

data class Hotels(
    val hotelId: Int,
    val hotelName: String,
    val country: String,
    val city: String,
    val stars: Int,
    val pricePerNight: Double,
    val continent: Continent
)
