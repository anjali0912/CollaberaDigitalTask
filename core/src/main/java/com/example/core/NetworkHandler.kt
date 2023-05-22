package com.example.core

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import okhttp3.OkHttpClient
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

const val TIME_OUT = 45L

class NetworkHandler(private val context: Context) {
    fun isConnected() =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).let { connectivityManager ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManager.activeNetwork != null
            } else {
                @Suppress("DEPRECATION")
                connectivityManager.activeNetworkInfo?.isConnected ?: false
            }
        }
}

val loggingInterceptor = HttpLoggingInterceptor()
    .apply { level = HttpLoggingInterceptor.Level.BODY }

fun getClient(
    timeOut: Long,
    listInterceptor: List<Interceptor> = listOf(loggingInterceptor)
): OkHttpClient = OkHttpClient.Builder()
    .readTimeout(timeOut, TimeUnit.SECONDS)
    .writeTimeout(timeOut, TimeUnit.SECONDS)
    .connectTimeout(timeOut, TimeUnit.SECONDS)
    .apply { listInterceptor.forEach { addInterceptor(it) } }
    .build()