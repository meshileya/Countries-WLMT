package com.example.countries_wlmt.domain

import com.example.countries_wlmt.data.model.CountryItem

interface GetCountriesUseCase {
    suspend fun execute(): List<CountryItem>
}
