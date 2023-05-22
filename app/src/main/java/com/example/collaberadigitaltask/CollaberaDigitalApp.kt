package com.example.collaberadigitaltask

import android.app.Application
import com.example.collaberadigitaltask.di.networkModule
import com.example.collaberadigitaltask.di.presentationModule
import com.example.data.di.*
import com.example.domain.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CollaberaDigitalApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CollaberaDigitalApp)
            modules(
                listOf(
                    databaseModule,
                    apiModule,
                    networkModule,
                    presentationModule,
                    useCaseModule,
                    repositoryModule,
                    sourceModule,
                    mapperModule,
                )
            )
        }
    }
}