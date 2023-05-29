package com.lacorp.weather_app.di

import com.lacorp.weather_app.data.repository.WeatherRepository
import com.lacorp.weather_app.data.source.remote.api.WeatherApi
import com.lacorp.weather_app.domain.repository.IWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        api: WeatherApi)
    : IWeatherRepository {
        return WeatherRepository(api)
    }
}