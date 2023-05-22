package com.example.domain.usecase.weather

import com.example.domain.model.weather.WeatherModel
import com.example.domain.params.weather.WeatherParams
import com.example.domain.repository.weather.WeatherRepository
import kotlin.coroutines.CoroutineContext
import com.example.core.Result
import com.example.core.Error

class WeatherUseCaseImpl(private val repository: WeatherRepository) :
    WeatherUseCase {
    override suspend fun execute(
        params: WeatherParams,
        coroutineContext: CoroutineContext
    ): Result<Error, WeatherModel> =
        repository.getWeather(params)
}