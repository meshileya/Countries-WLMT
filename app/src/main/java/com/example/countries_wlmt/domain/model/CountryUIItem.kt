package com.example.countries_wlmt.domain.model

import com.example.countries_wlmt.data.model.CountryItem


interface CountryUIItem {
    data class CountryUI(val countryItem: CountryItem) : CountryUIItem
    class HeaderUI(val header: String) : CountryUIItem
}
