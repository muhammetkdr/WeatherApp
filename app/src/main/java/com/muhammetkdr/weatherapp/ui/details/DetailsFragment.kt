package com.muhammetkdr.weatherapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.data.*
import com.muhammetkdr.weatherapp.base.BaseFragment
import com.muhammetkdr.weatherapp.common.extensions.EMPTY
import com.muhammetkdr.weatherapp.common.extensions.gone
import com.muhammetkdr.weatherapp.common.extensions.setLineChart
import com.muhammetkdr.weatherapp.common.extensions.visible
import com.muhammetkdr.weatherapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<FragmentDetailsBinding, DetailsViewModel>(FragmentDetailsBinding::inflate) {

    override val viewModel by viewModels<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLineChart()
    }

    private fun initLineChart() = viewModel.barEntry.observe(viewLifecycleOwner) {
        entyListObserver(it)
    }

    private fun entyListObserver(data : List<Entry>?) = with(binding){
        if(data==null){
            detailsProgressBar.visible()
        }else{
            detailsProgressBar.gone()
            val lineDataSet = LineDataSet(data,String.EMPTY)
            lineChartDetailsPage.setLineChart(lineDataSet, viewModel.hoursList)
            tvDetails.text = viewModel.datesAndTimes?.date
        }
    }
}