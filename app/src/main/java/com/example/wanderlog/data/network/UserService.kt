package com.example.wanderlog.data.network

import com.example.wanderlog.io.response.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST(value = "login")
    fun postLogin(@Query(value = "username") username: String, @Query(value = "password") password: String):
            Call<LoginResponse>
    companion object Factory{
        private const val BASE_URL = "http://localhost:8080/"
        fun create(): UserService {
            val retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(UserService::class.java)
        }
    }
}