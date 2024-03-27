package com.example.countries_wlmt

import com.example.countries_wlmt.data.model.CountryItem
import com.example.countries_wlmt.domain.GetCountriesUseCase

class MockedCountriesUseCase : GetCountriesUseCase {
    private var countries: List<CountryItem> = emptyList()
    private var shouldThrowException: Boolean = false

    fun setCountries(countries: List<CountryItem>) {
        this.countries = countries
    }

    fun setThrowException(shouldThrowException: Boolean) {
        this.shouldThrowException = shouldThrowException
    }

    override suspend fun execute(): List<CountryItem> {
        if (shouldThrowException) {
            throw Exception("Mocked exception for testing")
        }
        return countries
    }
}