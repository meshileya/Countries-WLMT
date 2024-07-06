package com.example.countries_wlmt.presentation.ui.cmpose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.countries_wlmt.domain.model.CountryUIItem
import com.example.countries_wlmt.presentation.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivityCompose : ComponentActivity() {
    @Inject
    lateinit var countryViewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val countries by countryViewModel.countriesLiveData.observeAsState(emptyList())

            var query by remember { mutableStateOf("") }
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "countryList") {
                composable("countryList") {
                    CountryListScreen(
                        countries = countries,
                        query = query,
                        onQueryChange = {
                            query = it
                            countryViewModel.searchCountries(it)
                        },
                        onCountryClick = { item ->
                            when (item) {
                                is CountryUIItem.CountryUI -> {
                                    navController.navigate("countryDetail/${item.countryItem.name}")
                                }

                                is CountryUIItem.HeaderUI -> {

                                }
                            }
                        }
                    )
                }
                composable(
                    "countryDetail/{countryName}",
                    arguments = listOf(navArgument("countryName") { type = NavType.StringType })
                ) { backStackEntry ->
                    CountryDetailScreen(
                        navController = navController,
                        countryName = backStackEntry.arguments?.getString("countryName")
                    )
                }
            }
            countryViewModel.getCountries()
        }
    }
}

@Composable
fun CountryListScreen(
    countries: List<CountryUIItem>,
    query: String,
    onQueryChange: (String) -> Unit,
    onCountryClick: (CountryUIItem) -> Unit
) {
    Column {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange
        )
        CountryList(
            countries = countries,
            onCountryClick = onCountryClick
        )
    }
}

@Composable
fun CountryList(
    countries: List<CountryUIItem>,
    onCountryClick: (CountryUIItem) -> Unit
) {
    LazyColumn {
        items(items = countries) { country ->
            when (country) {
                is CountryUIItem.HeaderUI -> {
                    PoseCountryHeaderItem(country)
                }

                is CountryUIItem.CountryUI -> {
                    PoseCountryBody(country, onCountryClick)
                }
            }

        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        label = { Text("Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}