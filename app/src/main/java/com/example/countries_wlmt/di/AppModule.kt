package com.example.countries_wlmt.di

import com.example.countries_wlmt.BuildConfig
import com.example.countries_wlmt.data.repository.CountryRepository
import com.example.countries_wlmt.data.repository.CountryRepositoryImpl
import com.example.countries_wlmt.data.repository.remote.CountryService
import com.example.countries_wlmt.domain.GetCountriesUseCase
import com.example.countries_wlmt.domain.GetCountriesUseCaseImpl
import com.example.countries_wlmt.presentation.CountryViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpLogginInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpLoggingInterceptor: HttpLoggingInterceptor): Retrofit {
        val client = OkHttpClient.Builder()
            .connectTimeout(BuildConfig.TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .readTimeout(BuildConfig.TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }

    @Provides
    @Singleton
    fun provideCountryService(retrofit: Retrofit): CountryService {
        return retrofit.create(CountryService::class.java)
    }

    @Provides
    @Singleton
    fun provideCountryRepository(countryService: CountryService): CountryRepository {
        return CountryRepositoryImpl(countryService)
    }

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(countryRepository: CountryRepository): GetCountriesUseCase {
        return GetCountriesUseCaseImpl(countryRepository)
    }

    @Provides
    @Singleton
    fun provideCountryViewModel(getCountriesUseCase: GetCountriesUseCase): CountryViewModel {
        return CountryViewModel(getCountriesUseCase)
    }

}