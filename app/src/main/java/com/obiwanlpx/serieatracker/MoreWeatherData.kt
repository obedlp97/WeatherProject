package com.obiwanlpx.serieatracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.obiwanlpx.serieatracker.API.WeatherApiService
import com.obiwanlpx.serieatracker.databinding.ActivityMoreWeatherDataBinding
import model.WeatherResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.obiwanlpx.serieatracker.databinding.ActivityWeatherFromApiBinding
import retrofit2.Response


class MoreWeatherData : AppCompatActivity() {

    private lateinit var apiService: WeatherApiService
    private lateinit var binding: ActivityMoreWeatherDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoreWeatherDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherstack.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(WeatherApiService::class.java)

        fetchWeather()
    }

    private fun fetchWeather() {
        val apiKey = "c350d95e7890611c7f181158af940e91"
        val location = "Turin"

        apiService.getCurrentWeather(apiKey, location)
            .enqueue(object : retrofit2.Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: retrofit2.Response<WeatherResponse>
                ) {
                    if (response.isSuccessful) {
                        val WeatherResponse = response.body()
                        WeatherResponse?.let {
                            val windspeed = it.current.wind_speed
                            val windDegree = it.current.wind_degree
                            val thePressure = it.current.pressure
                            val precipitation = it.current.precip
                            val weatherDescription =
                                it.current.weather_icons.firstOrNull() ?: "No disponible"

                            binding.windSpeedTextView.text = "Wind Speed: ${windspeed} km/h"
                            binding.windDegreeTextView.text = "Wind Degree: ${windDegree}"
                            binding.pressureTextView.text = "Pressure: ${thePressure} milibares"
                            binding.iconTextView.text = "Weather Description: ${weatherDescription}"

                            Log.d("Weather Info", "Temperature: $windspeed°C")
                            Log.d("Weather Info", "Description: $windDegree")
                            Log.d("Weather Info", "Humidity: $thePressure%")
                            Log.d("Weather Info", "Humidity: $weatherDescription%")
                        }
                    }else {
                            Log.e("API Error", "Error: ${response.code()}")
                        }
                    }
                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                        Log.e("API Error", "Failed: ${t.message}")
                    }
                })
            }
    }