package com.example.domain.params.recent

data class RecentParams(
    val tempMax: String = "",
    val tempMin: String = "",
    val name: String = "",
    val country: String = "",
    val sunrise: String = "",
    val sunset: String = ""
)