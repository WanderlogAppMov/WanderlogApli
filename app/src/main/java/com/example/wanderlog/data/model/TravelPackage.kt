package com.example.wanderlog.data.model

data class TravelPackage(
    val travelPackageId: Int,
    val destination: String,
    val hotel: Hotel,
    val restaurant: Restaurant,
    val flight: Flight,
    val attraction: Attraction,
    val pricePerStudent: Double,
    val agency: Agency,
    val continent: String
)

data class Hotel(
    val hotelId: Int,
    val hotelName: String,
    val country: String,
    val city: String,
    val stars: Int,
    val pricePerNight: Double,
    val continent: Continent
)

data class Restaurant(
    val restaurantId: Int,
    val restaurantName: String,
    val country: String,
    val city: String,
    val cuisineType: String,
    val priceRange: String,
    val continent: Continent
)

data class Flight(
    val flightId: Int,
    val airline: String,
    val departureCountry: String,
    val arrivalCountry: String,
    val price: Double,
    val continent: Continent
)

data class Attraction(
    val attractionId: Int,
    val attractionName: String,
    val country: String,
    val city: String,
    val ticketPrice: Double,
    val continent: Continent
)

data class Agency(
    val agencyId: Int,
    val organizationName: String,
    val repreFirstName: String,
    val repreLastName: String,
    val contactEmail: String,
)

data class Continent(
    val continentID: Int,
    val continentName: String
)

