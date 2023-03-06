package com.muhammetkdr.weatherapp.common.extensions

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.muhammetkdr.weatherapp.R


fun LineChart.setLineChart(dataSet: LineDataSet , value : List<String>) {
    isDragEnabled = true
    setScaleEnabled(false)

    dataSet.apply {
        fillAlpha = 150
        setDrawFilled(true)
        setColor(ContextCompat.getColor(context, R.color.light_gray))
        lineWidth = 1f
        valueTextSize = 10f
        setColors(*ColorTemplate.MATERIAL_COLORS)
    }

    xAxis.apply {
        axisMaximum = 0f
        axisMaximum = 7f
        textColor = Color.BLACK
        textSize = 10f
        setDrawGridLines(false)
        granularity = 1f
        position = XAxis.XAxisPosition.BOTTOM
        valueFormatter = IndexAxisValueFormatter(value)
    }

    legend.isEnabled = false
    description.text = String.EMPTY

    axisLeft.textColor = ContextCompat.getColor(context, R.color.teal_700)
    axisRight.textColor = ContextCompat.getColor(context, R.color.teal_700)

    val dataSets: ArrayList<ILineDataSet> = arrayListOf(dataSet)
    val lineData = LineData(dataSets)
    dataSet.setColors(*ColorTemplate.MATERIAL_COLORS)

    data = lineData
    invalidate()
}