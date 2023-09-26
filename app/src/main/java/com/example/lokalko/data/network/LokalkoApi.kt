package com.example.lokalko.data.network

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
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface LokalkoApi {
    @Headers("Content-Type: application/json")
    @POST("user/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("user/logout")
    suspend fun logout(): LogoutResponse

    @Headers("Content-Type: application/json")
    @POST("user/register")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse

    @GET("user/dashboard-analytics")
    suspend fun getAnalytics(): TotalRequests

    @GET("user/reported-issues")
    suspend fun getReportedIssues(): ReportedIssues

    @GET("user/resolved-issues")
    suspend fun getResolvedIssues(): ResolvedIssues

    @GET("user/categories")
    suspend fun getCategories(): Categories

    @GET("user/severities")
    suspend fun getSeverities(): Severities

    @GET("user/cities")
    suspend fun getCities(): Cities

    @GET("user/requests/user")
    suspend fun getRequests(@Query("user_id") userId: Int): Requests

    @GET("user/requests/city")
    suspend fun getRequestsByCity(@Query("city_id") cityId: Int): Requests

    @GET("user/city-latlang")
    suspend fun getCityData(@Query("city_id") cityId: Int): City

    @GET("user/requests/request")
    suspend fun getRequestDetails(@Query("request_id") requestId: Int): Request

    @Headers("Content-Type: application/json")
    @POST("user/request")
    suspend fun postRequest(@Body requestData: PostRequest): ServerResponse

    @GET("user/user-data")
    suspend fun getUserData(@Query("user_id") userId : Int): User

    @PUT("user/user-data")
    suspend fun updateUserData(@Query("user_id") userId: Int, @Body updateUser: UpdateUser): ServerResponse
}