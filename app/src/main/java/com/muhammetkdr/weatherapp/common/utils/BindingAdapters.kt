package com.muhammetkdr.weatherapp.common.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.muhammetkdr.weatherapp.R
import com.muhammetkdr.weatherapp.common.utils.Constants.IMAGE_RESOLUTION_SIZE
import com.muhammetkdr.weatherapp.common.utils.Constants.WEATHER_IMAGE_BASE_URL
import com.muhammetkdr.weatherapp.ui.search.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@BindingAdapter("setWeatherImage")
fun ImageView.setWeatherImage(weatherCondition:String?) {
    Glide.with(this).load(WEATHER_IMAGE_BASE_URL + weatherCondition + IMAGE_RESOLUTION_SIZE)
        .placeholder(R.drawable.ic_downloading)
        .error(R.drawable.ic_error)
        .into(this)
}

@BindingAdapter("app:addTextChangeListener")
fun addTextChangeListener(view: EditText, viewModel: SearchViewModel) {
    view.addTextChangedListener(object : TextWatcher {
        var job: Job? = null
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            job?.cancel()
        }

        override fun afterTextChanged(s: Editable?) {
            job = viewModel.viewModelScope.launch {
                delay(Constants.SEARCH_DELAY)
                viewModel.filterCityQuery(s)
            }
        }
    })
}