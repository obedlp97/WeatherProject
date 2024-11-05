package com.obiwanlpx.serieatracker.API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import model.WeatherResponse

interface WeatherApiService {
    @GET("current")
    fun getCurrentWeather(
        @Query("access_key") apiKey: String,
        @Query("query") location: String
    ): Call<WeatherResponse>
}