package com.example.collaberadigitaltask.di

import com.example.collaberadigitaltask.home.HomeData
import com.example.collaberadigitaltask.home.HomeViewModel
import com.example.collaberadigitaltask.login.LoginData
import com.example.collaberadigitaltask.login.LoginViewModel
import com.example.collaberadigitaltask.register.RegisterData
import com.example.collaberadigitaltask.register.RegisterViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val presentationModule = module {

    viewModel {
        LoginViewModel(
            data = LoginData(),
            application = androidApplication(),
            registerUseCase = get()
        )
    }

    viewModel {
        RegisterViewModel(
            data = RegisterData(),
            application = androidApplication(),
            registerUseCase = get()
        )
    }

    viewModel {
        HomeViewModel(
            data = HomeData(),
            application = androidApplication(),
            weatherUseCase = get(),
            recentUseCase = get()
        )
    }
}