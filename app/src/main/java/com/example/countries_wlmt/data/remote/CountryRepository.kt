package com.example.countries_wlmt.data.remote

import com.example.countries_wlmt.data.model.CountryItem

interface CountryRepository {
    suspend fun getCountries(): List<CountryItem>
}