package com.muhammetkdr.weatherapp.ui.details.chart

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.muhammetkdr.weatherapp.R
import com.muhammetkdr.weatherapp.common.extensions.EMPTY
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.DatesAndTimes

object LineChartFeeder {


    fun setLineChartData(
        chart: LineChart,
        dateAndHours: DatesAndTimes?,
        barEntries: List<BarEntry>,
        context: Context
    ) {
        val set1 = LineDataSet(barEntries, String.EMPTY)
        set1.valueFormatter = DatesAndTimesValueFormatter(dateAndHours)
        set1.valueTextSize = 9f
        set1.setDrawFilled(true)
        set1.valueTextColor = ContextCompat.getColor(context, R.color.black)
        set1.fillColor = ContextCompat.getColor(context, R.color.purple_200)
        set1.fillAlpha = 150
        set1.setCircleColor(ContextCompat.getColor(context, R.color.purple_200))
        val xAxis = chart.xAxis
        xAxis.textColor = ContextCompat.getColor(context, R.color.black)
        xAxis.valueFormatter = XAxisValueFormatter(dateAndHours)
        set1.color = ContextCompat.getColor(context, R.color.purple_200)
        val dataSets: java.util.ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(set1)
        val data = LineData(dataSets)
        chart.data = data
        //Set marker view
//        val markerView = WeightMarkerView(context, dateAndHours)
//        markerView.chartView = chart
//        chart.marker = markerView
        chart.invalidate()
    }

    fun LineChart.prepare(dataSet: LineDataSet) {
        isDragEnabled = true
        setScaleEnabled(false)
        dataSet.apply {
            fillAlpha = 110
            setColor(ContextCompat.getColor(context, R.color.teal_200))
            lineWidth = 1f
            valueTextSize = 10f
        }
        val dataSets: ArrayList<ILineDataSet> = arrayListOf(dataSet)
        val lineData = LineData(dataSets)
        dataSet.setColors(*ColorTemplate.MATERIAL_COLORS)
        xAxis.textColor = Color.DKGRAY
        xAxis.textSize = 12f
        data = lineData
        invalidate()

    }

}