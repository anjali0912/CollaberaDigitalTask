package com.example.data.remotesource.api

import com.example.data.entity.WeatherEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GetWeatherApi {
    @GET("/data/2.5/weather?")
    @Headers("Content-Type: application/json;charset=UTF-8")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("appid") appId: String,
    ): Response<WeatherEntity>
}