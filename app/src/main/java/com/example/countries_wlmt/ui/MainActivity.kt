package com.example.countries_wlmt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.countries_wlmt.R
import com.example.countries_wlmt.databinding.ActivityMainBinding
import com.example.countries_wlmt.presentation.CountryViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    @Inject
    lateinit var countryViewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the NavHostFragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostContainerView) as NavHostFragment

        // Get the NavController from the NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}