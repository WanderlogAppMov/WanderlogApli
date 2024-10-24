package com.example.wanderlog.data.network

import com.example.wanderlog.data.model.TravelPackage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TravelPackageService {

    @GET("api/travelpackages")
    fun getAllTravelPackages(): Call<List<TravelPackage>>

    @GET("api/travelpackages/{id}")
    fun getTravelPackageById(@Path("id") id: String): Call<TravelPackage>

    @POST("api/travelpackages")
    fun createTravelPackage(@Body newPackage: TravelPackage): Call<TravelPackage>

    @PUT("api/travelpackages/{id}")
    fun updateTravelPackage(@Path("id") id: String, @Body updatedPackage: TravelPackage): Call<TravelPackage>

    @DELETE("api/travelpackages/{id}")
    fun deleteTravelPackage(@Path("id") id: String): Call<Void>
}