package com.example.data.repository.weather

import com.example.data.remotesource.WeatherRemoteSource
import com.example.core.Error
import com.example.core.Result
import com.example.domain.model.weather.WeatherModel
import com.example.domain.params.weather.WeatherParams
import com.example.domain.repository.weather.WeatherRepository

class WeatherRepositoryImpl(private val source: WeatherRemoteSource) :
    WeatherRepository {

    override suspend fun getWeather(
        params: WeatherParams
    ): Result<Error, WeatherModel> = source.getWeather(
        parameter = params
    )
}