package com.example.wanderlog.data.network

import com.example.wanderlog.data.model.Hotels
import com.example.wanderlog.data.model.TravelPackage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface HotelService {


    @GET("api/hotels")
    fun getAllHotels(): Call<List<Hotels>>

    @GET("api/hotels/{id}")
    fun getTravelPackageById(@Path("id") id: String): Call<Hotels>

    @POST("api/hotels")
    fun createTravelPackage(@Body newPackage: TravelPackage): Call<Hotels>

    @PUT("api/hotels/{id}")
    fun updateTravelPackage(@Path("id") id: String, @Body updatedPackage: Hotels): Call<TravelPackage>

    @DELETE("api/hotels/{id}")
    fun deleteTravelPackage(@Path("id") id: String): Call<Void>
}