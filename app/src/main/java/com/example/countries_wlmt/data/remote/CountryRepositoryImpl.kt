package com.example.countries_wlmt.data.remote

import com.example.countries_wlmt.data.model.CountryItem

class CountryRepositoryImpl(private val countryService: CountryService) : CountryRepository {
    override suspend fun getCountries(): List<CountryItem> {
        try {
            return countryService.getCountriesAsync()
        } catch (ex: Exception) {
            ex.printStackTrace()
            throw CountryFetchException("Error fetching countries", ex)
        }
    }

}