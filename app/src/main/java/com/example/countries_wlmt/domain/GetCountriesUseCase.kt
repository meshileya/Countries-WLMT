package com.example.countries_wlmt.domain

import com.example.countries_wlmt.data.repository.Result
import com.example.countries_wlmt.domain.model.CountryUIItem

interface GetCountriesUseCase {
    suspend fun execute(): Result<List<CountryUIItem>>
}
