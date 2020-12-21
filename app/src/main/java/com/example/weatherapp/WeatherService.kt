package com.androimads.retrolin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/onecall?")
    fun getForecastWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("APPID") app_id: String, @Query("lang") lang: String): Call<WeatherResponse>
}

