package com.example.lokalko.data.network

import com.example.lokalko.data.model.Categories
import com.example.lokalko.data.model.Cities
import com.example.lokalko.data.model.Request
import com.example.lokalko.data.model.Requests
import com.example.lokalko.data.model.Severities
import com.example.lokalko.data.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface LokalkoApi {

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

    /*@Headers("Content-Type: application/json")
    @POST("user/request")
    suspend fun postRequest(@Body requestData: Request)*/

    @GET("user/{user_id}/user-data")
    suspend fun getUserData(@Path("user_id") userId: Int): User

}