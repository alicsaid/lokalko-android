package com.example.lokalko.data.model

import android.net.Uri
import okhttp3.RequestBody

data class LoginRequest(val email: String, val password: String)

data class RegisterRequest(
    val email: String,
    val password: String,
    val city: String,
    val firstName: String,
    val lastName: String
)

data class ServerResponse(val message: String)

data class TotalRequests(val totalRequests: Int)

data class Categories(val categories: List<Category>)

data class Category(val category_id: Int, val category: String)

data class Severities(val severities: List<Severity>)

data class Severity(val severity_id: Int, val severity: String)

data class Cities(val cities: List<City>)

data class City(val city_id: Int, val city: String)

data class Requests(val requests: List<Request>)

data class Request(
    val request_id: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val image: String,
    val address: String,
    val city: String,
    val email: String,
    val category: String,
    val severity: String,
    val service: String,
    val status: String
)

data class PostRequest(
    val title: RequestBody,
    val description: String,
    val address: String,
    val city: Int,
    val category: Int,
    val severity: Int,
    val images: List<Uri>
)

data class User(
    val user_id: Int,
    val email: String,
    val password: String,
    val city_id: Int,
    val first_name: String,
    val last_name: String,
    val city: String,
)

data class UpdateUser(
    val email: String,
    val first_name: String,
    val last_name: String,
    val city: String,
)
