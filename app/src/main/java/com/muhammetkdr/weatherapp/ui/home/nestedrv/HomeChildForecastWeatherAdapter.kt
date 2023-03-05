package com.muhammetkdr.weatherapp.ui.home.nestedrv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.weatherapp.databinding.ItemChildWeatherDaysBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.ChildRvUiData
import javax.inject.Inject

class
HomeChildForecastWeatherAdapter @Inject constructor() : RecyclerView.Adapter<HomeChildForecastWeatherAdapter.HomeChildForecastWeatherViewHolder>() {

    inner class HomeChildForecastWeatherViewHolder (val binding: ItemChildWeatherDaysBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(childRvUiData: ChildRvUiData) = with(binding) {
            childUiData = childRvUiData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeChildForecastWeatherViewHolder {
        val binding = ItemChildWeatherDaysBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeChildForecastWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeChildForecastWeatherViewHolder, position: Int) {
        val data = childRvUiData[position]

        holder.bind(data)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(data)
            }
        }

    }

    override fun getItemCount(): Int {
        return childRvUiData.size
    }

    private val diffUtilForHours = object : DiffUtil.ItemCallback<ChildRvUiData>() {
        override fun areItemsTheSame(oldItem: ChildRvUiData, newItem: ChildRvUiData): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: ChildRvUiData, newItem: ChildRvUiData): Boolean {
            return oldItem == newItem
        }
    }

    val differUidata = AsyncListDiffer(this, diffUtilForHours)


    private var childRvUiData: List<ChildRvUiData>
        get() = differUidata.currentList
        set(value) = differUidata.submitList(value)

    private var onItemClickListener: ((ChildRvUiData) -> Unit)? = null

    fun setOnChildItemClickListener(listener: (ChildRvUiData) -> Unit) {
        onItemClickListener = listener
    }

}