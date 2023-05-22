package com.example.domain.model.weather

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class WeatherModelTest {

    private val weatherModel = WeatherModel(
        base = BASE,
        latitude = LATITUDE,
        longitude = LONGITUDE,
        feelsLike = FEELS_LIKE,
        grandLevel = GRAND_LEVEL,
        humidity = HUMIDITY,
        pressure = PRESSURE,
        seaLevel = SEA_LEVEL,
        temp = TEMP,
        tempMax = TEMP_MAX,
        tempMin = TEMP_MIN,
        name = NAME,
        country = COUNTRY,
        sunrise = SUNRISE,
        sunset = SUNSET,
        timezone = TIMEZONE,
        visibility = VISIBILITY,
        deg = DEGREE,
        gust = GUST,
        speed = SPEED,
        weather = emptyList()
    )

    val weatherEmptyModel = WeatherModel()

    @Test
    fun getBase() {
        assertEquals(BASE, weatherModel.base)
        assertEquals("", weatherEmptyModel.base)
    }

    @Test
    fun getLatitude() {
        assertEquals(LATITUDE, weatherModel.latitude)
        assertEquals("", weatherEmptyModel.latitude)
    }

    @Test
    fun getLongitude() {
        assertEquals(LONGITUDE, weatherModel.longitude)
        assertEquals("", weatherEmptyModel.longitude)
    }

    @Test
    fun getFeelsLike() {
        assertEquals(FEELS_LIKE, weatherModel.feelsLike)
        assertEquals("", weatherEmptyModel.feelsLike)
    }

    @Test
    fun getGrandLevel() {
        assertEquals(GRAND_LEVEL, weatherModel.grandLevel)
        assertEquals("", weatherEmptyModel.grandLevel)
    }

    @Test
    fun getHumidity() {
        assertEquals(HUMIDITY, weatherModel.humidity)
        assertEquals("", weatherEmptyModel.humidity)
    }

    @Test
    fun getPressure() {
        assertEquals(PRESSURE, weatherModel.pressure)
        assertEquals("", weatherEmptyModel.pressure)
    }

    @Test
    fun getSeaLevel() {
        assertEquals(SEA_LEVEL, weatherModel.seaLevel)
        assertEquals("", weatherEmptyModel.seaLevel)
    }

    @Test
    fun getTemp() {
        assertEquals(TEMP, weatherModel.temp)
        assertEquals("", weatherEmptyModel.temp)
    }

    @Test
    fun getTempMax() {
        assertEquals(TEMP_MAX, weatherModel.tempMax)
        assertEquals("", weatherEmptyModel.tempMax)
    }

    @Test
    fun getTempMin() {
        assertEquals(TEMP_MIN, weatherModel.tempMin)
        assertEquals("", weatherEmptyModel.tempMin)
    }

    @Test
    fun getName() {
        assertEquals(NAME, weatherModel.name)
        assertEquals("", weatherEmptyModel.name)
    }

    @Test
    fun getCountry() {
        assertEquals(COUNTRY, weatherModel.country)
        assertEquals("", weatherEmptyModel.country)
    }

    @Test
    fun getSunrise() {
        assertEquals(SUNRISE, weatherModel.sunrise)
        assertEquals("", weatherEmptyModel.sunrise)
    }

    @Test
    fun getSunset() {
        assertEquals(SUNSET, weatherModel.sunset)
        assertEquals("", weatherEmptyModel.sunset)
    }

    @Test
    fun getTimezone() {
        assertEquals(TIMEZONE, weatherModel.timezone)
        assertEquals("", weatherEmptyModel.timezone)
    }

    @Test
    fun getVisibility() {
        assertEquals(VISIBILITY, weatherModel.visibility)
        assertEquals("", weatherEmptyModel.visibility)
    }

    @Test
    fun getDeg() {
        assertEquals(DEGREE, weatherModel.deg)
        assertEquals("", weatherEmptyModel.deg)
    }

    @Test
    fun getGust() {
        assertEquals(GUST, weatherModel.gust)
        assertEquals(0, weatherEmptyModel.gust)
    }

    @Test
    fun getSpeed() {
        assertEquals(SPEED, weatherModel.speed)
        assertEquals("", weatherEmptyModel.speed)
    }

    @Test
    fun getWeather() {
        assertEquals(emptyList<WeatherItem>(), weatherModel.weather)
        assertEquals(emptyList<WeatherItem>(), weatherEmptyModel.weather)
    }

    companion object {
        const val BASE = "base"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
        const val FEELS_LIKE = "feelsLike"
        const val GRAND_LEVEL = "grandLevel"
        const val HUMIDITY = "humidity"
        const val PRESSURE = "pressure"
        const val SEA_LEVEL = "seaLevel"
        const val TEMP = "temp"
        const val TEMP_MIN = "tempMin"
        const val TEMP_MAX = "tempMax"
        const val NAME = "name"
        const val COUNTRY = "country"
        const val SUNRISE = "sunrise"
        const val SUNSET = "sunset"
        const val TIMEZONE = "timezone"
        const val VISIBILITY = "visibility"
        const val DEGREE = "deg"
        const val GUST = 0
        const val SPEED = "SPEED"
    }
}