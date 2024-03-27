package com.example.countries_wlmt.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.countries_wlmt.common.hide
import com.example.countries_wlmt.common.show
import com.example.countries_wlmt.common.toast
import com.example.countries_wlmt.databinding.FragmentCountryBinding
import com.example.countries_wlmt.di.DaggerAppComponent
import com.example.countries_wlmt.presentation.CountryViewModel
import javax.inject.Inject

class FragmentCountry : Fragment() {

    @Inject
    lateinit var countryViewModel: CountryViewModel
    private lateinit var binding: FragmentCountryBinding
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerAppComponent.create().inject(this)
        countryAdapter = CountryAdapter {
            requireActivity().toast("Selected ${it.name ?: "NA"}")
        }
        binding.recyclerView.adapter = countryAdapter

        countryViewModel.countriesLiveData.observe(viewLifecycleOwner) { countries ->
            countryAdapter.updateList(countries)
            if (countries.isNullOrEmpty()) {
                binding.emptyStateTextView.show()
            } else {
                binding.emptyStateTextView.hide()
            }
        }

        countryViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.show()
            } else {
                binding.progressBar.hide()
            }

        }

        countryViewModel.exceptionMessage.observe(viewLifecycleOwner) { message ->
            requireActivity().toast(message)
        }

        countryViewModel.getCountries()

        binding.refreshLayout.setOnRefreshListener {
            countryViewModel.getCountries()
            binding.refreshLayout.isRefreshing = false
        }

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int
            ) {
                countryAdapter.filter(binding.searchEditText.text.toString())
            }
        })
    }

}