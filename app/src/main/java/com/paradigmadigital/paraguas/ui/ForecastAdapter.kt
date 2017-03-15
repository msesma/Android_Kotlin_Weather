package com.paradigmadigital.paraguas.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.paradigmadigital.paraguas.R
import com.paradigmadigital.paraguas.api.ImageRepository
import com.paradigmadigital.paraguas.api.model.ForecastItem
import javax.inject.Inject

class ForecastAdapter
@Inject
constructor(
        private val imageRepository: ImageRepository
) : RecyclerView.Adapter<ForecastViewHolder>() {

    private var forecast: List<ForecastItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.forecast_line, parent, false) as ViewGroup
        return ForecastViewHolder(view, imageRepository)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) = holder.bind(forecast[position])

    override fun getItemCount() = forecast.size

    fun swap(forecast: List<ForecastItem>) {
        this.forecast = forecast
        notifyDataSetChanged()
    }
}
