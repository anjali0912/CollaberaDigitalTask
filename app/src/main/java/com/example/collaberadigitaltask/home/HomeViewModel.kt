package com.example.collaberadigitaltask.home

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.collaberadigitaltask.BuildConfig
import com.example.core.BaseViewModel
import com.example.domain.model.recent.RecentEntity
import com.example.domain.model.weather.WeatherModel
import com.example.domain.params.recent.RecentParams
import com.example.domain.params.weather.WeatherParams
import com.example.domain.usecase.recent.RecentUseCase
import com.example.domain.usecase.weather.WeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application,
    data: HomeData,
    private val weatherUseCase: WeatherUseCase,
    private val recentUseCase: RecentUseCase
) : BaseViewModel<HomeData, HomeEvents>(application, data) {

    private var _state = mutableStateOf(HomeData())
    val state: State<HomeData> = _state

    val allRecentLocation: LiveData<List<RecentEntity>> = recentUseCase.getAllRecentLocation()

    init {
        getWeather()
    }

    private fun getWeatherParams(cityName: String = ""): WeatherParams {
        val name = cityName.ifEmpty {
            DEFAULT_CITY_NAME
        }
        return WeatherParams(name, BuildConfig.APIKEY)
    }

    private fun setWeatherModel(model: WeatherModel) {
        _state.value = state.value.copy(weatherModel = model)
    }

    private fun setLoadingState(loadingState: Boolean) {
        _state.value = state.value.copy(loadingState = loadingState)
    }

    private fun setErrorState(errorState: Boolean) {
        _state.value = state.value.copy(errorState = errorState)
    }

    fun getWeather(cityName: String = "") {
        setLoadingState(true)
        setErrorState(false)
        viewModelScope.launch {
            weatherUseCase.execute(getWeatherParams(cityName)).mapResult(
                success = { weatherModel ->
                    setWeatherModel(weatherModel)
                    mapToRecentParams(weatherModel)
                    setLoadingState(false)
                },
                failure = {
                    setLoadingState(false)
                    setErrorState(true)
                }
            )
        }
    }

    private fun mapToRecentParams(model: WeatherModel) {
        val recentParams = RecentParams(
            tempMax = model.tempMax,
            tempMin = model.tempMin,
            name = model.name,
            country = model.country,
            sunrise = model.sunrise,
            sunset = model.sunset
        )
        addRecentLocation(recentParams)
    }

    private fun addRecentLocation(params: RecentParams) =
        viewModelScope.launch(Dispatchers.IO) {
            recentUseCase.addRecentLocation(params)
        }

    companion object {
        const val DEFAULT_CITY_NAME = "Noida"
    }
}