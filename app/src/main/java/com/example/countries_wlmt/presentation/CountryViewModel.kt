package com.example.countries_wlmt.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries_wlmt.data.model.CountryItem
import com.example.countries_wlmt.data.remote.CountryFetchException
import com.example.countries_wlmt.domain.GetCountriesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryViewModel @Inject constructor(private val getCountriesUseCas: GetCountriesUseCase) :
    ViewModel() {
    private val _countreisLiveData = MutableLiveData<List<CountryItem>>()
    val countriesLiveData: LiveData<List<CountryItem>> = _countreisLiveData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get()  = _isLoading

    private val _exceptionMessage = MutableLiveData<String>()
    val exceptionMessage: LiveData<String> get() = _exceptionMessage

    init {
        _isLoading.value = false
    }

    fun getCountries() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val countries = getCountriesUseCas.execute()
                _countreisLiveData.value = countries
                _isLoading.value = false
            } catch (ex: CountryFetchException) {
                _countreisLiveData.value = emptyList()
                _isLoading.value = false
                _exceptionMessage.value = ex.message ?: "Something went wrong"
                ex.printStackTrace()
            } catch (ex: Exception) {
                _countreisLiveData.value = emptyList()
                _isLoading.value = false
                _exceptionMessage.value = ex.message ?: "Something went wrong"
                ex.printStackTrace()
            }

        }
    }
}