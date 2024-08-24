package com.taller1_cm.Logica

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherService {
    @GET("current.json")
    fun getCurrentWeather(@Query("q") city: String, @Query("key") apiKey: String): Call<WeatherResponse>
}