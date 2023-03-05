package com.muhammetkdr.weatherapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.muhammetkdr.weatherapp.base.BaseFragment
import com.muhammetkdr.weatherapp.common.extensions.EMPTY
import com.muhammetkdr.weatherapp.common.extensions.orZero
import com.muhammetkdr.weatherapp.databinding.FragmentDetailsBinding
import com.muhammetkdr.weatherapp.ui.details.chart.LineChartFeeder
import com.muhammetkdr.weatherapp.ui.details.chart.LineChartFeeder.prepare
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

        viewModel.datesAndTimes?.hours?.forEachIndexed { hourIndex, hourStrValue ->
            hoursIndexList.add(hourIndex.toFloat())
        }
            viewModel.datesAndTimes?.temperature?.forEach{ temp ->
                tempList.add(temp.toFloat().orZero())
            }
        for(item in hoursIndexList){
            barEntry.add(Entry(hoursIndexList[item.toInt()], tempList[item.toInt()]))
        }

        val lineDataSet = LineDataSet(barEntry,"Saatler arası sıcaklık değişimi")

        binding.lineChartDetailsPage.prepare(lineDataSet)
        lineDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)


//        val lineData = LineData(lineDataSet)
//        binding.lineChartDetailsPage.data = lineData

        binding.lineChartDetailsPage.description.text = String.EMPTY
        binding.lineChartDetailsPage.xAxis.valueFormatter = IndexAxisValueFormatter(viewModel.datesAndTimes!!.hours)
        binding.lineChartDetailsPage.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.lineChartDetailsPage.invalidate()


    }


}