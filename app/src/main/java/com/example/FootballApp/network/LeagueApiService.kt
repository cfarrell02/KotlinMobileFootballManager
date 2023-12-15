package com.example.FootballApp.network

import retrofit2.Retrofit
import retrofit2.http.GET
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType

private const val BASE_URL = "https://api-football-v1.p.rapidapi.com/v3/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface LeagueApiService {

    @GET("leagues")
    suspend fun getLeaguesByName(name: String): Map<String, String>



}
