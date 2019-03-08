package eu.sesma.paraguas.ui.master

import android.content.Context
import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import eu.sesma.paraguas.R
import eu.sesma.paraguas.api.ImageRepository
import eu.sesma.paraguas.domain.ForecastItem
import eu.sesma.paraguas.platform.format
import java.text.SimpleDateFormat

class ForecastViewHolder(
    itemView: ViewGroup,
    val imageRepo: ImageRepository
) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.icon)
    lateinit var icon: ImageView
    @BindView(R.id.temp)
    lateinit var temp: TextView
    @BindView(R.id.data)
    lateinit var data: TextView
    @BindView(R.id.data2)
    lateinit var data2: TextView
    @BindView(R.id.data3)
    lateinit var data3: TextView
    @BindView(R.id.hour)
    lateinit var hour: TextView

    lateinit private var forecastItem: ForecastItem
    private var forecastClickListener: ForecastClickListener? = null

    private val context: Context
    private val resources: Resources

    init {
        ButterKnife.bind(this, itemView)
        resources = itemView.resources
        context = itemView.context
    }

    fun bind(forecastItem: ForecastItem, forecastClickListener: ForecastClickListener?) {
        this.forecastItem = forecastItem
        this.forecastClickListener = forecastClickListener
        configureView()
    }

    private fun configureView() {
        icon.setImageResource(imageRepo.getCurrentIcon(forecastItem.iconName))

        val hr = SimpleDateFormat("HH").format(forecastItem.time).toInt()
        hour.setText("$hr")

        temp.setText(String.format(context.getString(R.string.number), forecastItem.temp))

        var dataText = "ºC "
        if (forecastItem.feelslike != forecastItem.temp) dataText += "(${forecastItem.feelslike}ºC) "
        data.setText(dataText)

        data2.setText("${forecastItem.windSpeed.format(0)} km/h")
        data3.setText("${forecastItem.rainProbability.format(0)}%")
    }

    @OnClick(R.id.forecast_row)
    internal fun onRowClick() {
        forecastClickListener?.onClick(adapterPosition)
    }
}
