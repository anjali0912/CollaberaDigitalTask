package com.example.domain.repository.weather

import com.example.domain.model.weather.WeatherModel
import com.example.core.Result
import com.example.core.Error
import com.example.domain.params.weather.WeatherParams

interface WeatherRepository {
    suspend fun getWeather(
        params: WeatherParams
    ): Result<Error, WeatherModel>
}