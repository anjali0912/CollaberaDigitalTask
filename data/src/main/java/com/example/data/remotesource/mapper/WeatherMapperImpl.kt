package com.example.data.remotesource.mapper

import com.example.data.entity.WeatherEntity
import com.example.domain.model.weather.WeatherItem
import com.example.domain.model.weather.WeatherModel

class WeatherMapperImpl : WeatherMapper {

    override fun toModel(entity: WeatherEntity): WeatherModel {
        return with(entity) {
            WeatherModel(
                base = this.base.orEmpty(),
                latitude = this.coord?.lat.toString().orEmpty(),
                longitude = this.coord?.lon.toString().orEmpty(),
                feelsLike = this.main?.feels_like.toString().orEmpty(),
                grandLevel = this.main?.grnd_level.toString().orEmpty(),
                humidity = this.main?.humidity.toString().orEmpty(),
                pressure = this.main?.pressure.toString().orEmpty(),
                seaLevel = this.main?.sea_level.toString().orEmpty(),
                temp = this.main?.temp.toString().orEmpty(),
                tempMin = this.main?.temp_min.toString().orEmpty(),
                tempMax = this.main?.temp_max.toString().orEmpty(),
                name = this.name.orEmpty(),
                country = this.sys?.country.orEmpty(),
                sunrise = this.sys?.sunrise.toString().orEmpty(),
                sunset = this.sys?.sunset.toString().orEmpty(),
                timezone = this.timezone.toString().orEmpty(),
                visibility = this.visibility.toString().orEmpty(),
                weather = this.weather?.map {
                    WeatherItem(
                        description = it.description.orEmpty(),
                        icon = it.icon.orEmpty(),
                        main = it.main.orEmpty()
                    )
                } ?: emptyList(),
                deg = this.wind?.deg.toString().orEmpty(),
                gust = this.wind?.gust?.toInt() ?: 0,
                speed = this.wind?.speed.toString().orEmpty(),
            )
        }
    }
}
