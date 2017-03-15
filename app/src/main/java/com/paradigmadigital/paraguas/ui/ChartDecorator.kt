package com.paradigmadigital.paraguas.ui

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.paradigmadigital.paraguas.R
import com.paradigmadigital.paraguas.api.model.ForecastItem
import java.text.SimpleDateFormat
import javax.inject.Inject


class ChartDecorator
@Inject
constructor() {

    //        https://github.com/PhilJay/MPAndroidChart/wiki/Getting-Started
    lateinit var chart: LineChart

    fun init(chart: LineChart) {
        this.chart = chart
    }

    fun setData(forecast: List<ForecastItem>) {

        val entries = ArrayList<Entry>()

        for (data in forecast) {
            val hour = SimpleDateFormat("HH").format(data.time).toFloat()
            entries.add(Entry(hour, data.temp))
        }
        val dataSet = LineDataSet(entries, "")
        dataSet.setColor(R.color.colorPrimary);

        val lineData = LineData(dataSet)
        chart.setData(lineData)
        chart.invalidate()
    }

}