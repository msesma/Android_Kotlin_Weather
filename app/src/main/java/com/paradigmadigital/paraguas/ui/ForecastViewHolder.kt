package com.paradigmadigital.paraguas.ui

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.paradigmadigital.paraguas.R
import com.paradigmadigital.paraguas.api.ImageRepository
import com.paradigmadigital.paraguas.api.model.ForecastItem
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.text.SimpleDateFormat

class ForecastViewHolder(itemView: ViewGroup, val imageRepo: ImageRepository) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.icon)
    lateinit var icon: ImageView
    @BindView(R.id.temp)
    lateinit var temp: TextView
    @BindView(R.id.data)
    lateinit var data: TextView

    lateinit private var forecastItem: ForecastItem

    private val context: Context
    private val resources: Resources

    private val iconTarget = object : Target {

        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
            icon.setImageBitmap(bitmap)
        }

        override fun onBitmapFailed(errorDrawable: Drawable?) {
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        }
    }

    init {
        ButterKnife.bind(this, itemView)
        resources = itemView.resources
        context = itemView.context
    }

    fun bind(forecastItem: ForecastItem) {
        this.forecastItem = forecastItem
        configureView()
    }

    private fun configureView() {
        imageRepo.getCurrentIcon(forecastItem.iconUrl, iconTarget)
        val hour = SimpleDateFormat("HH").format(forecastItem.time).toFloat()
        temp.setText("$hour: ${forecastItem.temp} ºC")
        data.setText("(${forecastItem.feelslike}ºC) ${forecastItem.condition} ${forecastItem.windSpeed}kmh ${forecastItem.rainProbability}%")
    }
}
