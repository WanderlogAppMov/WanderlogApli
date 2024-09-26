package com.example.wanderlog

object FavoriteManager {
    private val favoritePackages = mutableListOf<TravelPackage>()

    fun addPackage(travelPackage: TravelPackage) {
        if (!favoritePackages.contains(travelPackage)) {
            favoritePackages.add(travelPackage)
        }
    }

    fun removePackage(travelPackage: TravelPackage) {
        favoritePackages.remove(travelPackage)
    }

    fun getFavoritePackages(): List<TravelPackage> {
        return favoritePackages
    }
}