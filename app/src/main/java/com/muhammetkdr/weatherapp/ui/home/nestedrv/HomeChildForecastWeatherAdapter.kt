package com.muhammetkdr.weatherapp.ui.home.nestedrv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList
import com.muhammetkdr.weatherapp.databinding.ItemChildWeatherDaysBinding
import javax.inject.Inject

class
HomeChildForecastWeatherAdapter @Inject constructor() : RecyclerView.Adapter<HomeChildForecastWeatherAdapter.HomeChildForecastWeatherViewHolder>() {

    inner class HomeChildForecastWeatherViewHolder (val binding: ItemChildWeatherDaysBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hours: String, weatherList: WeatherList) = with(binding) {
            binding.hour = hours
            binding.weatherList = weatherList
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeChildForecastWeatherViewHolder {
        val binding = ItemChildWeatherDaysBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeChildForecastWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeChildForecastWeatherViewHolder, position: Int) {
        val hour = hours[position]
        val weather = weatherList[position]
        holder.bind(hour, weather)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(weather)
            }
        }

    }

    override fun getItemCount(): Int {
        return hours.size
    }

    private val diffUtilForHours = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differForHours = AsyncListDiffer(this, diffUtilForHours)

    private val diffUtilForWeatherList = object : DiffUtil.ItemCallback<WeatherList>() {
        override fun areItemsTheSame(oldItem: WeatherList, newItem: WeatherList): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: WeatherList, newItem: WeatherList): Boolean {
            return oldItem == newItem
        }
    }

    val differForWeatherList = AsyncListDiffer(this, diffUtilForWeatherList)

    private var hours: List<String>
        get() = differForHours.currentList
        set(value) = differForHours.submitList(value)

    private var weatherList: List<WeatherList>
        get() = differForWeatherList.currentList
        set(value) = differForWeatherList.submitList(value)

    private var onItemClickListener: ((WeatherList) -> Unit)? = null

    fun setOnChildItemClickListener(listener: (WeatherList) -> Unit) {
        onItemClickListener = listener
    }

}