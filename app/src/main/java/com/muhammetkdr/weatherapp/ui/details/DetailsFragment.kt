package com.muhammetkdr.weatherapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.muhammetkdr.weatherapp.base.BaseFragment
import com.muhammetkdr.weatherapp.common.extensions.orZero
import com.muhammetkdr.weatherapp.common.extensions.setLineChart
import com.muhammetkdr.weatherapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment :
    BaseFragment<FragmentDetailsBinding, DetailsViewModel>(FragmentDetailsBinding::inflate) {

    override val viewModel by viewModels<DetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvDetails.text = viewModel.datesAndTimes?.date

        initLineChart()


    }

    private fun initLineChart() {

        // BarEntry( 1. si index  -->> hour index, 2. si tempreture )

        val tempList : MutableList<Float> = mutableListOf()
        val hoursIndexList : MutableList<Float> = mutableListOf()

        val barEntry = arrayListOf<Entry>()
        // val hourString = ArrayList<String>()  -->    viewmodel.hours already exist

        viewModel.datesAndTimes?.childRvUiData?.forEachIndexed{ index , uiData ->
            hoursIndexList.add(index.toFloat())
            tempList.add(uiData.temperature.toFloat().orZero())
        }

        for(item in hoursIndexList){
            barEntry.add(Entry(hoursIndexList[item.toInt()], tempList[item.toInt()]))
        }

        val lineDataSet = LineDataSet(barEntry,"Saatler arası sıcaklık değişimi")

        binding.lineChartDetailsPage.setLineChart(lineDataSet)
        binding.lineChartDetailsPage.xAxis.valueFormatter = IndexAxisValueFormatter(viewModel.datesAndTimes?.hours)

    }

}