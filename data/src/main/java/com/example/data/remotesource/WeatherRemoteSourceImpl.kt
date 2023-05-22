package com.example.data.remotesource

import com.example.core.Result
import com.example.core.Error
import com.example.data.remotesource.api.GetWeatherApi
import com.example.data.remotesource.mapper.WeatherMapper
import com.example.domain.model.weather.WeatherModel
import com.example.domain.params.weather.WeatherParams

class WeatherRemoteSourceImpl(
    private val getWeatherApi: GetWeatherApi,
    val mapper: WeatherMapper
) : WeatherRemoteSource {

    override suspend fun getWeather(
        parameter: WeatherParams
    ): Result<Error, WeatherModel> =
        execute(
            service = {
                getWeatherApi.getWeather(
                    cityName = parameter.cityName,
                    appId = parameter.appId
                )
            },
            success = { entity, _ -> mapper.toModel(entity) },
            failure = { Error.RemoteError() }
        )
}