package com.example.lokalko.data.repository

import com.example.lokalko.data.model.Categories
import com.example.lokalko.data.model.Cities
import com.example.lokalko.data.model.Request
import com.example.lokalko.data.model.Requests
import com.example.lokalko.data.model.Severities
import com.example.lokalko.data.model.User
import com.example.lokalko.data.network.LokalkoApi
import javax.inject.Inject

interface LokalkoRepository {

    suspend fun getCategories(): Categories
    suspend fun getSeverities(): Severities
    suspend fun getCities(): Cities
    suspend fun getRequests(): Requests
    suspend fun getRequestsByCity(): Requests
    suspend fun getRequestDetails(requestId: Int): Request
    //suspend fun postRequest(requestData: Request)
    suspend fun getUserData(userId: Int): User
}

class LokalkoRepositoryImpl @Inject constructor(private val lokalkoApi: LokalkoApi):
    LokalkoRepository {

    override suspend fun getCategories(): Categories = lokalkoApi.getCategories()
    override suspend fun getSeverities(): Severities = lokalkoApi.getSeverities()
    override suspend fun getCities(): Cities = lokalkoApi.getCities()
    override suspend fun getRequests(): Requests = lokalkoApi.getRequests()
    override suspend fun getRequestsByCity(): Requests = lokalkoApi.getRequestsByCity()
    override suspend fun getRequestDetails(requestId: Int): Request = lokalkoApi.getRequestDetails(requestId)
    //override suspend fun postRequest(requestData: Request) = lokalkoApi.postRequest(requestData)
    override suspend fun getUserData(userId: Int): User = lokalkoApi.getUserData(userId)
}
