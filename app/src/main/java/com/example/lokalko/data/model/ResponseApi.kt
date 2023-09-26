package com.example.lokalko.data.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val email: String,
    val password: String,
    val city: String,
    val firstName: String,
    val lastName: String
)

data class LoginResponse(
    val token: String
)

data class LogoutResponse(
    val token: String
)

data class ReportData(
    val title: String,
    val description: String,
    val address: String,
    val cityId: Int,
    val categoryId: Int,
    val severityId: Int
)

data class RegisterResponse(
    val token: String
)

data class ServerResponse(
    val message: String
)

data class TotalRequests(
    val totalRequests: Int
)

data class ReportedIssues(
    val reportedIssues: Int
)

data class ResolvedIssues(
    val resolvedIssues: Int
)

data class Categories(
    val categories: List<Category>
)

data class Category(
    val category_id: Int,
    val category: String
)

data class Severities(
    val severities: List<Severity>
)

data class Severity(
    val severity_id: Int,
    val severity: String
)

data class Cities(
    val cities: List<City>
)

data class City(
    val city_id: Int,
    val city: String,
    val latitude: Double,
    val longitude: Double
)

data class Requests(
    val requests: List<Request>
)

data class Request(
    val request_id: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val city: String,
    val email: String,
    val category: String,
    val severity: String,
    val service: String,
    val status: String
)

data class PostRequest(
    val title: String,
    val description: String,
    val address: String,
    val city_id: Int,
    val category_id: Int,
    val severity_id: Int,
    val latitude: Double,
    val longitude: Double
)

data class User(
    val user_id: Int,
    val email: String,
    val city_id: Int,
    val first_name: String,
    val last_name: String,
    val city: String
)

data class UpdateUser(
    val first_name: String,
    val last_name: String,
    val city: String,
    val email: String
)