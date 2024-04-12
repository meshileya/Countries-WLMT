package com.example.countries_wlmt.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries_wlmt.domain.GetCountriesUseCase
import com.example.countries_wlmt.domain.model.CountryUIItem
import com.example.countries_wlmt.domain.model.Result
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountryViewModel @Inject constructor(private val getCountriesUseCas: GetCountriesUseCase) :
    ViewModel() {
    private val _countreisLiveData = MutableLiveData<List<CountryUIItem>>()
    val countriesLiveData: LiveData<List<CountryUIItem>> = _countreisLiveData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _exceptionMessage = MutableLiveData<String>()
    val exceptionMessage: LiveData<String> get() = _exceptionMessage

    var job: Job? = null

    init {
        _isLoading.value = false
    }

    fun getCountries() {
        _isLoading.value = true
        job?.cancel()
        job = viewModelScope.launch {
            when (val result = getCountriesUseCas.execute()) {
                is Result.Success -> {
                    _countreisLiveData.value = result.data
                    _isLoading.value = false
                }

                is Result.Error -> {
                    _exceptionMessage.value = result.message ?: "Something went wrong"
                }
            }
            _isLoading.value = false

        }

    }
}