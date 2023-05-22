package com.example.collaberadigitaltask.home

import com.example.core.BaseData
import com.example.domain.model.weather.WeatherModel

data class HomeData(
    val weatherModel: WeatherModel = WeatherModel(),
    val loadingState: Boolean = false,
    val errorState: Boolean = false
) : BaseData()