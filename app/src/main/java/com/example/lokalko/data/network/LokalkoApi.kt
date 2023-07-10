package com.example.lokalko.data.network

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
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface LokalkoApi {
    @Headers("Content-Type: application/json")
    @POST("user/login")
    suspend fun login(@Body loginRequest: LoginRequest): ServerResponse

    @GET("user/logout")
    suspend fun logout(): ServerResponse

    @Headers("Content-Type: application/json")
    @POST("user/register")
    suspend fun register(@Body registerRequest: RegisterRequest): ServerResponse

    @GET("user/dashboard-analytics")
    suspend fun getAnalytics(@Query("user_id") userId: Int): TotalRequests

    @GET("user/categories")
    suspend fun getCategories(): Categories

    @GET("user/severities")
    suspend fun getSeverities(): Severities

    @GET("user/cities")
    suspend fun getCities(): Cities

    @GET("user/requests/1/user") //1 = alicsaid
    suspend fun getRequests(): Requests

    @GET("user/requests/2/city") //2 = Konjic
    suspend fun getRequestsByCity(): Requests

    @GET("user/requests/{request_id}/request")
    suspend fun getRequestDetails(@Path("request_id") requestId: Int): Request

//    @Multipart
//    @POST("user/request")
//    suspend fun postRequest(
//        @Part("requestData") requestData: RequestBody,
//        @Part images: List<MultipartBody.Part>
//    ): RequestResponse

    @GET("user/13/user-data")
    suspend fun getUserData(): User

    @PUT("user/{user_id}/user-data")
    suspend fun updateUserData(@Path("user_id") userId: Int, updateUser: UpdateUser): ServerResponse
}