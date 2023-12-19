package com.example.inventory.network
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


private const val BASE_URL = "https://api-football-v1.p.rapidapi.com/v3/"
private const val API_KEY = "e4b4d5da80msh1dc208234b8bcaap19675fjsn80876f57d3c4"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory(contentType = "application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface LeagueAPIService {
    @GET("leagues")
    suspend fun getLeagues(
        @Header("X-Rapidapi-Key") apiKey: String = API_KEY,
        @Query("name") leagueName: String
    ): LeagueApiResponse

}