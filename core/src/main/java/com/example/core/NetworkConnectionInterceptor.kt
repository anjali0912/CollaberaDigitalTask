package com.example.core

import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val networkHandler: NetworkHandler) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkHandler.isConnected()) {
            throw NoConnectivityException()
        }
        val builder = chain.request().newBuilder().build()
        return chain.proceed(builder)
    }
}