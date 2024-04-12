package com.example.countries_wlmt.domain.model

import com.example.countries_wlmt.data.model.CountryItem


sealed class CountryUIItem {
    data class CountryUI(val countryItem: CountryItem) : CountryUIItem()
    data class HeaderUI(val header: String) : CountryUIItem()
}
