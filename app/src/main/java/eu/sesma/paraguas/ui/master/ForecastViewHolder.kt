package eu.sesma.paraguas.ui.master

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.view.ViewGroup
import eu.sesma.paraguas.R
import eu.sesma.paraguas.api.ImageRepository
import eu.sesma.paraguas.domain.ForecastItem
import eu.sesma.paraguas.platform.format
import kotlinx.android.synthetic.main.forecast_line.view.*
import java.text.SimpleDateFormat

class ForecastViewHolder(
    private val view: ViewGroup,
    private val imageRepo: ImageRepository
) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    private lateinit var forecastItem: ForecastItem
    private var forecastClickListener: ForecastClickListener? = null

    private val context: Context
    private val resources: Resources = itemView.resources

    init {
        context = itemView.context
    }

    fun bind(forecastItem: ForecastItem, forecastClickListener: ForecastClickListener?) {
        this.forecastItem = forecastItem
        this.forecastClickListener = forecastClickListener
        configureView()
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun configureView() {
        view.icon.setImageResource(imageRepo.getCurrentIcon(forecastItem.iconName))

        val hr = SimpleDateFormat("HH").format(forecastItem.time).toInt()
        view.hour.text = "$hr"

        view.temp.text = String.format(context.getString(R.string.number), forecastItem.temp)

        var dataText = "ºC "
        if (forecastItem.feelslike != forecastItem.temp) dataText += "(${forecastItem.feelslike}ºC) "
        view.data.text = dataText

        view.data2.text = "${forecastItem.windSpeed.format(0)} km/h"
        view.data3.text = "${(forecastItem.rainProbability * 100).format(0)}%"

        view.forecast_row.setOnClickListener { forecastClickListener }
    }
}
