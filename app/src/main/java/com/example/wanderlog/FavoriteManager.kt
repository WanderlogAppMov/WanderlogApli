package com.example.wanderlog

import com.example.wanderlog.data.model.TravelPackage

object FavoriteManager {
    private val favoritePackages = mutableListOf<TravelPackage>()

    // Obtener la lista de favoritos
    fun getFavoritePackages(): List<TravelPackage> {
        return favoritePackages
    }

    // Agregar un paquete a los favoritos
    fun addPackage(travelPackage: TravelPackage) {
        if (!favoritePackages.contains(travelPackage)) {
            favoritePackages.add(travelPackage)
        }
    }

    // Eliminar un paquete de los favoritos
    fun removePackage(travelPackage: TravelPackage) {
        favoritePackages.remove(travelPackage)
    }

    // Verificar si un paquete es favorito
    fun isFavorite(travelPackage: TravelPackage): Boolean {
        return favoritePackages.contains(travelPackage)
    }
}
