package com.example.countries_wlmt.domain

import com.example.countries_wlmt.data.remote.CountryFetchException
import com.example.countries_wlmt.data.remote.CountryRepository
import com.example.countries_wlmt.domain.model.CountryUIItem

class GetCountriesUseCaseImpl(private val countryRepository: CountryRepository) :
    GetCountriesUseCase {
    override suspend fun execute(): List<CountryUIItem> {
        try {
            val countries = countryRepository.getCountries().sortedBy { it.name }

            val listItems = mutableListOf<CountryUIItem>()
            var currentLetter: Char? = null

            countries.forEach {
                val countryChar = it.name.first()
                if (currentLetter == null || countryChar != currentLetter) {
                    listItems.add(CountryUIItem.HeaderUI("${countryChar}"))
                    currentLetter = countryChar
                }
                listItems.add(CountryUIItem.CountryUI(it))
            }

            return listItems
        } catch (ex: CountryFetchException) {
            ex.printStackTrace()
            throw CountryFetchException(ex.message ?: "Failed to execute countryUseCaseImp", ex)
        } catch (ex: Exception) {
            throw CountryFetchException("Failed to execute countryUseCaseImp", ex)
        }
    }

}