package com.example.countries_wlmt.domain

import com.example.countries_wlmt.data.repository.CountryRepository
import com.example.countries_wlmt.domain.model.CountryUIItem
import com.example.countries_wlmt.domain.model.Result

class GetCountriesUseCaseImpl(private val countryRepository: CountryRepository) :
    GetCountriesUseCase {
    override suspend fun execute(): Result<List<CountryUIItem>> {
        try {
            when (val result = countryRepository.getCountries()) {
                is Result.Success -> {
                    val listItems = mutableListOf<CountryUIItem>()
                    var currentLetter: Char? = null

                    result.data.sortedBy { it.name }.forEach {
                        val countryChar = it.name.first()
                        if (currentLetter == null || countryChar != currentLetter) {
                            listItems.add(CountryUIItem.HeaderUI("$countryChar"))
                            currentLetter = countryChar
                        }
                        listItems.add(CountryUIItem.CountryUI(it))

                    }
                    return Result.Success(listItems)
                }

                is Result.Error -> {
                    return result
                }
            }
        } catch (ex: Exception) {
            return Result.Error(-99, ex.message ?: "Failed to execute countryUseCaseImp")
        }
    }

}