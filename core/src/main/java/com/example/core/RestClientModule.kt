package com.example.core

import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClientModule {

    fun build(
        baseUrl: String,
        client: OkHttpClient,
        listConverterFactory: List<Converter.Factory> = listOf(GsonConverterFactory.create())
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .apply { listConverterFactory.forEach { addConverterFactory(it) } }
        .build()
}
