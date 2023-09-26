package com.example.lokalko.data.repository

import com.example.lokalko.data.helpers.Result
import com.example.lokalko.data.helpers.handleApiException
import com.example.lokalko.data.helpers.handleRegisterApiException
import com.example.lokalko.data.model.Categories
import com.example.lokalko.data.model.Cities
import com.example.lokalko.data.model.City
import com.example.lokalko.data.model.LoginRequest
import com.example.lokalko.data.model.LoginResponse
import com.example.lokalko.data.model.LogoutResponse
import com.example.lokalko.data.model.PostRequest
import com.example.lokalko.data.model.RegisterRequest
import com.example.lokalko.data.model.RegisterResponse
import com.example.lokalko.data.model.ReportedIssues
import com.example.lokalko.data.model.Request
import com.example.lokalko.data.model.Requests
import com.example.lokalko.data.model.ResolvedIssues
import com.example.lokalko.data.model.ServerResponse
import com.example.lokalko.data.model.Severities
import com.example.lokalko.data.model.TotalRequests
import com.example.lokalko.data.model.UpdateUser
import com.example.lokalko.data.model.User
import com.example.lokalko.data.network.LokalkoApi
import java.lang.Exception
import javax.inject.Inject

interface LokalkoRepository {

    suspend fun login(email: String, password: String): Result<LoginResponse>
    suspend fun logout(): Result<LogoutResponse>
    suspend fun register(
        email: String,
        password: String,
        city: String,
        firstName: String,
        lastName: String
    ): Result<RegisterResponse>

    suspend fun getCategories(): Categories
    suspend fun getAnalytics(): TotalRequests
    suspend fun getReportedIssues(): ReportedIssues
    suspend fun getResolvedIssues(): ResolvedIssues
    suspend fun getSeverities(): Severities
    suspend fun getCities(): Cities
    suspend fun getRequests(userId: Int): Requests
    suspend fun getRequestsByCity(cityId: Int): Requests
    suspend fun getCityData(cityId: Int): City
    suspend fun getRequestDetails(requestId: Int): Request
    suspend fun postRequest(
        title: String,
        description: String,
        address: String,
        city_id: Int,
        category_id: Int,
        severity_id: Int,
        latitude: Double,
        longitude: Double
    ): ServerResponse

    suspend fun getUserData(userId: Int): User
    suspend fun updateUser(userId: Int, updateUser: UpdateUser): ServerResponse
}

class LokalkoRepositoryImpl @Inject constructor(private val lokalkoApi: LokalkoApi) :
    LokalkoRepository {

    override suspend fun login(email: String, password: String): Result<LoginResponse> {
        val request = LoginRequest(email, password)
        return try {
            Result.Success(lokalkoApi.login(request))
        } catch (ex: Exception) {
            Result.Failure(handleApiException(ex))
        }
    }

    override suspend fun logout(): Result<LogoutResponse> {
        return try {
            val response = lokalkoApi.logout()
            Result.Success(response)
        } catch (ex: Exception) {
            Result.Failure(handleApiException(ex))
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        city: String,
        firstName: String,
        lastName: String,
    ): Result<RegisterResponse> {
        val registerRequest = RegisterRequest(email, password, city, firstName, lastName)
        return try {
            Result.Success(lokalkoApi.register(registerRequest))
        } catch (ex: Exception) {
            Result.Failure(handleRegisterApiException(ex))
        }
    }

    override suspend fun getAnalytics(): TotalRequests = lokalkoApi.getAnalytics()
    override suspend fun getReportedIssues(): ReportedIssues = lokalkoApi.getReportedIssues()
    override suspend fun getResolvedIssues(): ResolvedIssues = lokalkoApi.getResolvedIssues()
    override suspend fun getCategories(): Categories = lokalkoApi.getCategories()
    override suspend fun getSeverities(): Severities = lokalkoApi.getSeverities()
    override suspend fun getCities(): Cities = lokalkoApi.getCities()
    override suspend fun getRequests(userId: Int): Requests = lokalkoApi.getRequests(userId)
    override suspend fun getRequestsByCity(cityId: Int): Requests =
        lokalkoApi.getRequestsByCity(cityId)

    override suspend fun getCityData(cityId: Int): City = lokalkoApi.getCityData(cityId)
    override suspend fun getRequestDetails(requestId: Int): Request =
        lokalkoApi.getRequestDetails(requestId)

    override suspend fun postRequest(
        title: String,
        description: String,
        address: String,
        city_id: Int,
        category_id: Int,
        severity_id: Int,
        latitude: Double,
        longitude: Double,
    ): ServerResponse {
        val postRequest =
            PostRequest(
                title,
                description,
                address,
                city_id,
                category_id,
                severity_id,
                latitude,
                longitude
            )
        return lokalkoApi.postRequest(postRequest)
    }

    override suspend fun getUserData(userId: Int): User = lokalkoApi.getUserData(userId)

    override suspend fun updateUser(userId: Int, updateUser: UpdateUser): ServerResponse {
        return lokalkoApi.updateUserData(userId, updateUser)
    }
}
