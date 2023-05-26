package com.muhammetkdr.weatherapp.ui.search.rv

import com.muhammetkdr.weatherapp.base.BaseViewHolder
import com.muhammetkdr.weatherapp.databinding.ItemSearchResponseBinding
import com.muhammetkdr.weatherapp.ui.search.SearchUiData
import javax.inject.Inject

class CitiesRvViewHolder @Inject constructor(
    private val binding: ItemSearchResponseBinding,
    private val onItemClickListener: ((SearchUiData) -> Unit)?
) : BaseViewHolder<SearchUiData>(binding.root) {
    override fun onBind(data: SearchUiData) {
        binding.searchUiData = data
        itemView.setOnClickListener {
            onItemClickListener?.invoke(data)
        }
    }
}