package com.example.countries_wlmt.di

import com.example.countries_wlmt.ui.FragmentCountry
import com.example.countries_wlmt.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)

    fun inject(fragment: FragmentCountry)
}