package com.example.data.di

import com.example.core.REST_PUBLIC_CLIENT_MODULE
import com.example.data.localsource.recent.RecentLocalSource
import com.example.data.localsource.recent.RecentLocalSourceImpl
import com.example.data.localsource.recent.database.RecentDatabase
import com.example.data.localsource.recent.mapper.RecentMapper
import com.example.data.localsource.recent.mapper.RecentMapperImpl
import com.example.data.localsource.register.RegisterLocalSource
import com.example.data.localsource.register.RegisterLocalSourceImpl
import com.example.data.localsource.register.database.UserDatabase
import com.example.data.localsource.register.mapper.RegisterMapper
import com.example.data.localsource.register.mapper.RegisterMapperImpl
import com.example.data.remotesource.WeatherRemoteSource
import com.example.data.remotesource.WeatherRemoteSourceImpl
import com.example.data.remotesource.api.GetWeatherApi
import com.example.data.remotesource.mapper.WeatherMapper
import com.example.data.remotesource.mapper.WeatherMapperImpl
import com.example.data.repository.recent.RecentRepositoryImpl
import com.example.data.repository.register.RegisterRepositoryImpl
import com.example.data.repository.weather.WeatherRepositoryImpl
import com.example.domain.repository.recent.RecentRepository
import com.example.domain.repository.register.RegisterRepository
import com.example.domain.repository.weather.WeatherRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single<GetWeatherApi> { get<Retrofit>(named(REST_PUBLIC_CLIENT_MODULE)).create(GetWeatherApi::class.java) }
}

val sourceModule = module {
    single<RegisterLocalSource> {
        RegisterLocalSourceImpl(
            userDao = get(),
            mapper = get()
        )
    }

    single<RecentLocalSource> {
        RecentLocalSourceImpl(
            recentDao = get(),
            mapper = get()
        )
    }

    single<WeatherRemoteSource> {
        WeatherRemoteSourceImpl(
            getWeatherApi = get(), mapper = get()
        )
    }
}

val repositoryModule = module {
    single<RegisterRepository> { RegisterRepositoryImpl(source = get()) }
    single<RecentRepository> { RecentRepositoryImpl(source = get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(source = get()) }
}

val mapperModule = module {
    single<RegisterMapper> { RegisterMapperImpl() }
    single<RecentMapper> { RecentMapperImpl() }
    single<WeatherMapper> { WeatherMapperImpl() }
}

val databaseModule = module {
    single { UserDatabase.getDatabase(get()).getUserDao() }
    single { RecentDatabase.getDatabase(get()).getRecentDao() }
}