package com.example.lokalko.data.repository

import com.example.lokalko.data.model.Categories
import com.example.lokalko.data.model.Cities
import com.example.lokalko.data.model.LoginRequest
import com.example.lokalko.data.model.PostRequest
import com.example.lokalko.data.model.RegisterRequest
import com.example.lokalko.data.model.Request
import com.example.lokalko.data.model.Requests
import com.example.lokalko.data.model.ServerResponse
import com.example.lokalko.data.model.Severities
import com.example.lokalko.data.model.TotalRequests
import com.example.lokalko.data.model.UpdateUser
import com.example.lokalko.data.model.User
import com.example.lokalko.data.network.LokalkoApi
import okhttp3.MultipartBody
import javax.inject.Inject

interface LokalkoRepository {

    suspend fun login(email: String, password: String): ServerResponse?
    suspend fun logout(): ServerResponse?
    suspend fun register(
        email: String,
        password: String,
        city: String,
        firstName: String,
        lastName: String
    ): ServerResponse?

    suspend fun getCategories(): Categories
    suspend fun getAnalytics(userId: Int): TotalRequests
    suspend fun getSeverities(): Severities
    suspend fun getCities(): Cities
    suspend fun getRequests(): Requests
    suspend fun getRequestsByCity(): Requests
    suspend fun getRequestDetails(requestId: Int): Request

    //suspend fun postRequest(requestData: PostRequest, images: List<MultipartBody.Part>): RequestResponse
    suspend fun getUserData(): User
    suspend fun updateUser(userId: Int, updateUser: UpdateUser): ServerResponse

}

class LokalkoRepositoryImpl @Inject constructor(private val lokalkoApi: LokalkoApi) :
    LokalkoRepository {

    override suspend fun login(email: String, password: String): ServerResponse {
        val request = LoginRequest(email, password)
        val response = lokalkoApi.login(request)
        return response.let {
            ServerResponse(it.message)
        }
    }

    override suspend fun logout(): ServerResponse = lokalkoApi.logout()
    override suspend fun register(
        email: String,
        password: String,
        city: String,
        firstName: String,
        lastName: String
    ): ServerResponse {
        val registerRequest = RegisterRequest(email, password, city, firstName, lastName)
        return lokalkoApi.register(registerRequest)
    }

    override suspend fun getAnalytics(userId: Int): TotalRequests = lokalkoApi.getAnalytics(userId)
    override suspend fun getCategories(): Categories = lokalkoApi.getCategories()
    override suspend fun getSeverities(): Severities = lokalkoApi.getSeverities()
    override suspend fun getCities(): Cities = lokalkoApi.getCities()
    override suspend fun getRequests(): Requests = lokalkoApi.getRequests()
    override suspend fun getRequestsByCity(): Requests = lokalkoApi.getRequestsByCity()
    override suspend fun getRequestDetails(requestId: Int): Request = lokalkoApi.getRequestDetails(requestId)

//    override suspend fun postRequest(requestData: PostRequest, images: List<MultipartBody.Part>): RequestResponse = lokalkoApi.postRequest(requestData, images)
    override suspend fun getUserData(): User = lokalkoApi.getUserData()
    override suspend fun updateUser(userId: Int, updateUser: UpdateUser): ServerResponse {
        return lokalkoApi.updateUserData(userId, updateUser)
    }
}
