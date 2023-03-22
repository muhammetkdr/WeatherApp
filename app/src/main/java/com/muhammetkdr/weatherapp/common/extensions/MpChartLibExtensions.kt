package com.muhammetkdr.weatherapp.common.extensions

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.muhammetkdr.weatherapp.R

fun LineChart.setLineChart(dataSet: LineDataSet , value : List<String>) {

    isDragEnabled = true
    setScaleEnabled(false)
    setTouchEnabled(true)

    with(dataSet){
        fillAlpha = 150
        setDrawFilled(true)
        color = ContextCompat.getColor(context, R.color.light_blue100)
        lineWidth = 1f
        valueTextSize = 10f
        setColors(getColor(R.color.light_blue100))
        valueTextColor= ContextCompat.getColor(context,R.color.white)
        mode= LineDataSet.Mode.CUBIC_BEZIER
        fillDrawable= ContextCompat.getDrawable(context,R.drawable.mpchart_linechart_background)
    }

    with(xAxis){
        axisMaximum = 0f
        axisMaximum = 7f
        textColor = Color.WHITE
        textSize = 10f
        setDrawGridLines(false)
        setDrawAxisLine(false)
        granularity = 1f
        position = XAxis.XAxisPosition.BOTTOM
        valueFormatter = IndexAxisValueFormatter(value)
    }
    setDrawBorders(false)

    legend.isEnabled = false
    description.text = String.EMPTY

    axisLeft.setDrawGridLines(false)
    axisLeft.textColor = ContextCompat.getColor(context, R.color.light_blue100)
    axisRight.isEnabled = false

    val dataSets: ArrayList<ILineDataSet> = arrayListOf(dataSet)
    val lineData = LineData(dataSets)

    data = lineData
    invalidate()
}