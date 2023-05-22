package com.example.data.remotesource.mapper

import com.example.data.entity.WeatherEntity
import com.example.domain.model.weather.WeatherModel

interface WeatherMapper {
    fun toModel(entity: WeatherEntity): WeatherModel
}