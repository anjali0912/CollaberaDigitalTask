package com.example.domain.model.weather

data class WeatherModel(
    val base: String = "",
    val latitude: String = "",
    val longitude: String = "",
    val feelsLike: String = "",
    val grandLevel: String = "",
    val humidity: String = "",
    val pressure: String = "",
    val seaLevel: String = "",
    val temp: String = "",
    val tempMax: String = "",
    val tempMin: String = "",
    val name: String = "",
    val country: String = "",
    val sunrise: String = "",
    val sunset: String = "",
    val timezone: String = "",
    val visibility: String = "",
    val deg: String = "",
    val gust: Int = 0,
    val speed: String = "",
    val weather: List<WeatherItem> = emptyList()
)

data class WeatherItem(
    val description: String = "",
    val icon: String = "",
    val main: String = ""
)