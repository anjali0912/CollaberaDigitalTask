package com.example.domain.usecase.weather

import com.example.core.BaseUseCase
import com.example.domain.model.weather.WeatherModel
import com.example.domain.params.weather.WeatherParams

interface WeatherUseCase : BaseUseCase<WeatherParams, WeatherModel>