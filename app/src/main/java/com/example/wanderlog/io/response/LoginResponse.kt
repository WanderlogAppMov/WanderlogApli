package com.example.wanderlog.io.response

import com.example.wanderlog.data.model.User

data class LoginResponse(
    val success: Boolean,
    val user: User,
    val jwt: String
)
