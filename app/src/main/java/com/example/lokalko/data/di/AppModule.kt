package com.example.lokalko.data.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.lokalko.data.helpers.BASE_URL
import com.example.lokalko.data.network.CustomInterceptor
import com.example.lokalko.data.network.LokalkoApi
import com.example.lokalko.data.preferences.Preferences
import com.example.lokalko.data.preferences.PreferencesImpl
import com.example.lokalko.data.repository.LokalkoRepository
import com.example.lokalko.data.repository.LokalkoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideHttpClient(preferences: Preferences) = OkHttpClient.Builder()
        .addInterceptor(CustomInterceptor(preferences))
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        .build()

    @Singleton
    @Provides
    fun provideLokalkoApi(okHttpClient: OkHttpClient): LokalkoApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(LokalkoApi::class.java)

    @Singleton
    @Provides
    fun provideLokalkoRepositoryImpl(lokalkoApi: LokalkoApi) =
        LokalkoRepositoryImpl(lokalkoApi) as LokalkoRepository

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application) =
        application.getSharedPreferences("shared_pref", MODE_PRIVATE)

    @Singleton
    @Provides
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences =
        PreferencesImpl(sharedPreferences)
}