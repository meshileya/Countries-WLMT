package com.example.countries_wlmt.data.repository

import com.example.countries_wlmt.data.model.CountryItem
import com.example.countries_wlmt.data.repository.remote.CountryService
import retrofit2.HttpException

class CountryRepositoryImpl(private val countryService: CountryService) : CountryRepository {
    override suspend fun getCountries(): Result<List<CountryItem>> {
        return try {
            Result.Success(countryService.getCountriesAsync())
        } catch (ex: Exception) {
            if (ex is HttpException) {
                Result.Error(ex.code(), ex.message())
            } else {
                Result.Error(-1, ex.message)
            }
        }
    }
}