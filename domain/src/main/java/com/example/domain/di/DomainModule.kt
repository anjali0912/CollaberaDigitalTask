package com.example.domain.di

import com.example.domain.usecase.recent.RecentUseCase
import com.example.domain.usecase.recent.RecentUseCaseImpl
import com.example.domain.usecase.register.RegisterUseCase
import com.example.domain.usecase.register.RegisterUseCaseImpl
import com.example.domain.usecase.weather.WeatherUseCase
import com.example.domain.usecase.weather.WeatherUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<RegisterUseCase> { RegisterUseCaseImpl(repository = get()) }
    single<RecentUseCase> { RecentUseCaseImpl(repository = get()) }
    single<WeatherUseCase> { WeatherUseCaseImpl(repository = get()) }
}