package eu.sesma.paraguas.ui.detail

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import eu.sesma.paraguas.R
import eu.sesma.paraguas.api.ImageRepository
import eu.sesma.paraguas.domain.ForecastItem
import kotlinx.android.synthetic.main.activity_detail.view.*
import java.text.SimpleDateFormat
import javax.inject.Inject

class DetailActivityDecorator
@Inject
constructor(
    private val activity: AppCompatActivity,
    private val imageRepo: ImageRepository
) : DetailActivityUserInterface {

    private lateinit var view: View

    fun bind(view: View) {
        this.view = view
        initToolbar()
    }

    @SuppressLint("SimpleDateFormat")
    override fun initialize(forecastItem: ForecastItem?) {
        view.toolbar.title = SimpleDateFormat("HH:00").format(forecastItem?.time)
        showForecast(forecastItem)
    }

    private fun showForecast(forecast: ForecastItem?) {
        view.icon.setImageResource(imageRepo.getCurrentIcon(forecast?.iconName ?: ""))
        view.condition.text = forecast?.condition
        view.temp.text = String.format(activity.getString(R.string.temp), forecast?.temp)
        view.feelslike.text =
            String.format(activity.getString(R.string.feels_like), forecast?.feelslike)
        view.rain.text = String.format(
            activity.getString(R.string.rain),
            (forecast?.rainProbability ?: 0.0) * 100,
            forecast?.rainQuantity
        )
        view.humidity.text =
            String.format(activity.getString(R.string.humidity), forecast?.humidity)
        view.snow.text = String.format(activity.getString(R.string.snow), forecast?.snow)
        view.wind.text = String.format(activity.getString(R.string.wind), forecast?.windSpeed)
    }

    private fun initToolbar() {
        activity.setSupportActionBar(view.toolbar)
        val actionBar = activity.supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
