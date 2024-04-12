package com.example.countries_wlmt.data.repository

import com.example.countries_wlmt.data.model.CountryItem
import com.example.countries_wlmt.domain.model.Result

interface CountryRepository {
    suspend fun getCountries(): Result<List<CountryItem>>
}