package com.muhammetkdr.weatherapp.ui.search.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.weatherapp.base.BaseListAdapter
import com.muhammetkdr.weatherapp.databinding.ItemSearchResponseBinding
import com.muhammetkdr.weatherapp.ui.search.SearchUiData
import javax.inject.Inject

class CitiesRvAdapter @Inject constructor(
    private val onItemClickListener: ((SearchUiData) -> Unit)?
) : BaseListAdapter<SearchUiData>(
    itemsSame = { old, new -> old == new },
    contentsSame = { old, new -> old == new }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder  {

        val binding = ItemSearchResponseBinding.inflate(inflater,parent,false)
        return CitiesRvViewHolder(binding,onItemClickListener)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CitiesRvViewHolder -> {
                getItem(position)?.let { item ->
                    holder.onBind(item)
                }
            }
        }
    }
}