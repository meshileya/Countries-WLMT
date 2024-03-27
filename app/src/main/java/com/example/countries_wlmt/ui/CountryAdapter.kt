package com.example.countries_wlmt.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countries_wlmt.data.model.CountryItem
import com.example.countries_wlmt.databinding.ListingCountryBinding
import java.util.Locale

@SuppressLint("NotifyDataSetChanged")
class CountryAdapter(
    val callback: (CountryItem) -> Unit
) :
    RecyclerView.Adapter<CountryAdapter.CustomViewHolder>() {
    var countryItems = ArrayList<CountryItem>()
    var countryItemsCopy = ArrayList<CountryItem>()


    fun updateList(list: List<CountryItem>) {
        this.countryItems.clear()
        this.countryItemsCopy.clear()
        if (list.isNotEmpty()) {
            this.countryItems.addAll(list)
            this.countryItemsCopy.addAll(list)
        }
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        countryItems.clear()
        val lowercaseQuery = query.lowercase(Locale.getDefault())
        countryItems.addAll(if (lowercaseQuery.isEmpty()) {
            countryItemsCopy
        } else {
            countryItemsCopy.filter { item ->
                item.name?.lowercase(Locale.getDefault())?.contains(lowercaseQuery) == true ||
                        item.capital?.lowercase(Locale.getDefault())
                            ?.contains(lowercaseQuery) == true
            }
        })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder.from(
            parent
        )
    }

    override fun getItemCount(): Int = countryItems.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = countryItems[position]
        holder.itemView.setOnClickListener {
            callback(item)
        }
        holder.bind(countryItems[position])
    }

    class CustomViewHolder(private val binding: ListingCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CountryItem) {
            val region = data.region ?: "N/A"
            val countryNameWithRegion = "Country: ${data.name ?: "N/A"}, $region"

            binding.nameTextView.text = countryNameWithRegion

//            val region = data.region ?: "N/A"
//            binding.regionTextView.text = region

            val countryCode = "Code: ${data.code}"
            binding.codeTextView.text = countryCode

            val capital = "Capital: ${data.capital ?: "N/A"}"
            binding.capitalTextView.text = capital

        }

        companion object {
            fun from(parent: ViewGroup): CustomViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListingCountryBinding.inflate(layoutInflater, parent, false)
                return CustomViewHolder(
                    binding
                )
            }
        }
    }
}