package com.example.lokalko.data.di

import com.example.lokalko.data.network.LokalkoApi
import com.example.lokalko.data.repository.LokalkoRepository
import com.example.lokalko.data.repository.LokalkoRepositoryImpl
import com.example.lokalko.data.helpers.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideLokalkoApi(): LokalkoApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LokalkoApi::class.java)

    @Singleton
    @Provides
    fun provideLokalkoRepositoryImpl(lokalkoApi: LokalkoApi) = LokalkoRepositoryImpl(lokalkoApi) as LokalkoRepository
}