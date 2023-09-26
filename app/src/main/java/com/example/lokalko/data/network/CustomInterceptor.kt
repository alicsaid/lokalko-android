package com.example.lokalko.data.network

import com.example.lokalko.data.preferences.Preferences
import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor(private val preferences: Preferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("authorization", preferences.readToken() ?: "")
            .build()
        return chain.proceed(request)
    }
}