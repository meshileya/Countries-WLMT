package com.example.countries_wlmt.domain

import com.example.countries_wlmt.domain.model.CountryUIItem

interface GetCountriesUseCase {
        suspend fun execute(): List<CountryUIItem>
//    suspend fun execute(): HashMap<String, List<CountryItem>>
}
