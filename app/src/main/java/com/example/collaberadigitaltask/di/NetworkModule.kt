package com.example.collaberadigitaltask.di

import com.example.core.BASE_URL
import com.example.core.NETWORK_CONNECTIVITY_INTERCEPTOR
import com.example.core.NetworkConnectionInterceptor
import com.example.core.NetworkHandler
import com.example.core.REST_PUBLIC_CLIENT_MODULE
import com.example.core.RestClientModule
import com.example.core.TIME_OUT
import com.example.core.getClient
import com.example.core.loggingInterceptor
import okhttp3.Interceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {

    single {
        NetworkHandler(get())
    }

    single<Interceptor>(named(NETWORK_CONNECTIVITY_INTERCEPTOR)) {
        NetworkConnectionInterceptor(get())
    }

    single(named(REST_PUBLIC_CLIENT_MODULE)) {
        RestClientModule.build(
            BASE_URL,
            getClient(
                TIME_OUT,
                listOf(
                    get(named(NETWORK_CONNECTIVITY_INTERCEPTOR)),
                    loggingInterceptor
                )
            )
        )
    }
}