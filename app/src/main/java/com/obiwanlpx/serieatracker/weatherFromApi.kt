package com.obiwanlpx.serieatracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.obiwanlpx.serieatracker.API.WeatherApiService
import model.WeatherResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.obiwanlpx.serieatracker.databinding.ActivityWeatherFromApiBinding

class WeatherDetailActivity : AppCompatActivity() {

    private lateinit var apiService: WeatherApiService
    private lateinit var binding: ActivityWeatherFromApiBinding // Declara el binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el binding
        binding = ActivityWeatherFromApiBinding.inflate(layoutInflater)
        setContentView(binding.root) // Establece el contenido de la vista usando el binding

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

        apiService.getCurrentWeather(apiKey, location).enqueue(object : retrofit2.Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: retrofit2.Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    weatherResponse?.let {
                        // Obtén los datos del clima
                        val temperature = it.current.temperature
                        val weatherDescription = it.current.weather_descriptions.firstOrNull() ?: "No disponible"
                        val humidity = it.current.humidity

                        // Muestra los datos en los TextViews del layout utilizando el binding
                        binding.locationTextView.text = "Locación: ${it.location.name}, ${it.location.country}"
                        binding.temperatureTextView.text = "Temperature: $temperature°C"
                        binding.weatherDescriptionTextView.text = "Weather Description: $weatherDescription"
                        binding.humidityTextView.text = "Humidity: $humidity%"

                        // Log para depuración
                        Log.d("Weather Info", "Temperature: $temperature°C")
                        Log.d("Weather Info", "Description: $weatherDescription")
                        Log.d("Weather Info", "Humidity: $humidity%")
                    }
                } else {
                    Log.e("API Error", "Error: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("API Error", "Failed: ${t.message}")
            }
        })
    }
}