package com.example.countries_wlmt.domain

import com.example.countries_wlmt.data.model.CountryItem
import com.example.countries_wlmt.data.remote.CountryFetchException
import com.example.countries_wlmt.data.remote.CountryRepository

class GetCountriesUseCaseImpl(private val countryRepository: CountryRepository) :
    GetCountriesUseCase {
    override suspend fun execute(): List<CountryItem> {
        try {
            return countryRepository.getCountries()
        } catch (ex: CountryFetchException) {
            ex.printStackTrace()
            throw CountryFetchException(ex.message ?: "Failed to execute countryUseCaseImp", ex)
        } catch (ex: Exception) {
            throw CountryFetchException("Failed to execute countryUseCaseImp", ex)
        }
    }

}