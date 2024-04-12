package com.example.countries_wlmt.data.repository

import com.example.countries_wlmt.data.model.CountryItem

interface CountryRepository {
    suspend fun getCountries(): Result<List<CountryItem>>
}