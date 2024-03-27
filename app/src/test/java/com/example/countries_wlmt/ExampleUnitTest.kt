package com.example.countries_wlmt

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.countries_wlmt.commons.getOrAwaitLiveDataValue
import com.example.countries_wlmt.data.model.CountryItem
import com.example.countries_wlmt.presentation.CountryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    private lateinit var viewModel: CountryViewModel

    private lateinit var mockedCountriesUseCase: MockedCountriesUseCase


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        mockedCountriesUseCase = MockedCountriesUseCase()
        viewModel = CountryViewModel(mockedCountriesUseCase)
    }

    @Test
    fun `get countries - success`() = runTest{
        // Given
        val countries = listOf(
            CountryItem(
                capital = "Ottawa",
                code = "CA",
                currency = null,
                demonym = null,
                flag = null,
                language = null,
                name = "Canada",
                region = null
            )
        )
        mockedCountriesUseCase.setCountries(countries)

        // When
        viewModel.getCountries()

        // Then
        val result = viewModel.countriesLiveData.getOrAwaitLiveDataValue()
        assertEquals(countries, result)
    }

    @Test
    fun `get countries - error`() = runTest {
        // Given
        mockedCountriesUseCase.setThrowException(true)

        // When
        viewModel.getCountries()

        // Then
        val result = viewModel.countriesLiveData.getOrAwaitLiveDataValue()
        assertEquals(null, result)
    }
}