package com.muhammetkdr.weatherapp.ui.search.rv

import com.muhammetkdr.weatherapp.base.BaseViewHolder
import com.muhammetkdr.weatherapp.databinding.ItemSearchResponseBinding
import com.muhammetkdr.weatherapp.domain.entity.cities.CitiesEntity
import javax.inject.Inject

class CitiesRvViewHolder @Inject constructor(
    private val binding: ItemSearchResponseBinding,
    private val onItemClickListener: ((CitiesEntity) -> Unit)?
) : BaseViewHolder<CitiesEntity>(binding.root) {
    override fun onBind(data: CitiesEntity) {
        binding.citiesEntity = data
        itemView.setOnClickListener {
            onItemClickListener?.invoke(data)
        }
    }
}