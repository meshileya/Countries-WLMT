package com.example.countries_wlmt.presentation.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countries_wlmt.data.model.CountryItem
import com.example.countries_wlmt.databinding.ListingCountryBinding
import com.example.countries_wlmt.databinding.ListingHeaderBinding
import com.example.countries_wlmt.domain.model.CountryUIItem

@SuppressLint("NotifyDataSetChanged")
class CountryAdapter(
    val callback: (CountryUIItem) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var countryItems = ArrayList<CountryUIItem>()
    var countryItemsCopy = ArrayList<CountryUIItem>()

    companion object {
        val HEADER = 0
        val COUNTRY = 1
    }

    fun updateList(list: List<CountryUIItem>) {
        this.countryItems.clear()
        this.countryItemsCopy.clear()
        if (list.isNotEmpty()) {
            this.countryItems.addAll(list)
            this.countryItemsCopy.addAll(list)
        }
        notifyDataSetChanged()
    }

    fun filter(query: String) {
//        countryItems.clear()
//        val lowercaseQuery = query.lowercase(Locale.getDefault())
//        countryItems.addAll(if (lowercaseQuery.isEmpty()) {
//            countryItemsCopy
//        } else {
//            countryItemsCopy.filter { item ->
//                item.name?.lowercase(Locale.getDefault())?.contains(lowercaseQuery) == true ||
//                        item.capital?.lowercase(Locale.getDefault())
//                            ?.contains(lowercaseQuery) == true
//            }
//        })
//        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        // compute if it's HEADER or COUNTRY for a given position r
        return when (countryItems[position]) {
            is CountryUIItem.CountryUI -> COUNTRY
            is CountryUIItem.HeaderUI -> HEADER
            else -> {
                HEADER
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> {
                CustomHeaderViewHolder.from(parent)
            }

            COUNTRY -> {
                CustomBodyViewHolder.from(parent)
            }

            else -> {
                CustomBodyViewHolder.from(parent)
            }
        }

    }

    override fun getItemCount(): Int = countryItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            HEADER -> {
                val headerHolder = holder as? CustomHeaderViewHolder
                headerHolder?.bind(countryItems[position] as CountryUIItem.HeaderUI)
            }

            COUNTRY -> {
                val bodyHolder = holder as? CustomBodyViewHolder
                bodyHolder?.bind((countryItems[position] as CountryUIItem.CountryUI).countryItem)
            }

        }
    }

    class CustomHeaderViewHolder(private val binding: ListingHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CountryUIItem.HeaderUI) {
            binding.headerTv.text = data.header
        }

        companion object {
            fun from(parent: ViewGroup): CustomHeaderViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListingHeaderBinding.inflate(layoutInflater, parent, false)
                return CustomHeaderViewHolder(
                    binding
                )

            }
        }
    }

    class CustomBodyViewHolder(private val binding: ListingCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CountryItem) {
            val region = data.region ?: "N/A"
            val countryNameWithRegion = "Country: ${data.name ?: "N/A"}, $region"


            binding.nameTextView.text = countryNameWithRegion

            val countryCode = "Code: ${data.code}"
            binding.codeTextView.text = countryCode

            val capital = "Capital: ${data.capital ?: "N/A"}"
            binding.capitalTextView.text = capital

        }

        companion object {
            fun from(parent: ViewGroup): CustomBodyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListingCountryBinding.inflate(layoutInflater, parent, false)
                return CustomBodyViewHolder(
                    binding
                )

            }
        }
    }

}