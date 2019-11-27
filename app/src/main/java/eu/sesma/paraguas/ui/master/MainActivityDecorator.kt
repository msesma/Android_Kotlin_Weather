package eu.sesma.paraguas.ui.master

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import eu.sesma.paraguas.R
import eu.sesma.paraguas.api.ImageRepository
import eu.sesma.paraguas.domain.Astronomy
import eu.sesma.paraguas.domain.CurrentWeather
import eu.sesma.paraguas.domain.ForecastItem
import kotlinx.android.synthetic.main.activity_main.view.*
import java.text.SimpleDateFormat
import javax.inject.Inject

class MainActivityDecorator
@Inject
constructor(
    val activity: AppCompatActivity,
    private val imagerepo: ImageRepository,
    private val layoutManager: androidx.recyclerview.widget.LinearLayoutManager,
    val adapter: ForecastAdapter,
    private val graph: Graph
) : MainActivityUserInterface {

    private val TAG = MainActivityDecorator::class.simpleName

    private lateinit var view: View

    private var delegate: MainActivityUserInterface.Delegate? = null
    private var city: String? = null

    private val forecastClickListener = object : ForecastClickListener {
        override fun onClick(index: Int) {
            delegate?.onClick(adapter.getItemAtPosition(index))
        }
    }

    var refreshListener: SwipeRefreshLayout.OnRefreshListener =
        SwipeRefreshLayout.OnRefreshListener { delegate?.onRefresh() }

    fun bind(view: View) {
        this.view = view
        initToolbar()
        view.forecast_list.layoutManager = layoutManager
        view.forecast_list.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        view.swipeRefreshLayout.setOnRefreshListener(refreshListener)
        setWaitingMode(true)
    }

    fun dispose() {
        delegate = null
    }

    override fun initialize(delegate: MainActivityUserInterface.Delegate) {
        this.delegate = delegate
        view.toolbar.title = ""
        view.forecast_list.adapter = adapter
        adapter.setClickListener(forecastClickListener)
    }

    override fun showError(error: Exception) {
        activity.runOnUiThread {
            setWaitingMode(false)
            Log.e(TAG, error.toString())
            view.temp.text = activity.getString(R.string.connection_error)
        }
    }

    override fun showCurrentWeather(currentWeather: CurrentWeather) {
        setWaitingMode(false)
        view.icon.setImageResource(imagerepo.getCurrentIcon(currentWeather.iconName))
        view.condition.text = currentWeather.condition
        view.temp.text = String.format(activity.getString(R.string.number), currentWeather.temp)
        view.feelslike.text =
            String.format(activity.getString(R.string.feels_like), currentWeather.feelsLike)
        graph.currentWeather = currentWeather
        graph.draw(view.graph)
    }

    @SuppressLint("SimpleDateFormat")
    override fun showCurrentAstronomy(astronomy: Astronomy) {
        setWaitingMode(false)
        val sunrise = SimpleDateFormat("HH:mm").format(astronomy.sunrise)
        val sunset = SimpleDateFormat("HH:mm").format(astronomy.sunset)
        view.daylight.text = String.format(activity.getString(R.string.daylight), sunrise, sunset)
        graph.astronomy = astronomy
        graph.draw(view.graph)
    }

    override fun showForecast(forecast: List<ForecastItem>) {
        setWaitingMode(false)
        view.forecast_list.visibility = if (forecast.isEmpty()) INVISIBLE else VISIBLE
        adapter.swap(forecast)
        graph.forecast = forecast
        graph.draw(view.graph)
    }

    override fun setCity(city: String) {
        this.city = city
        view.toolbar.title = city
    }

    private fun setWaitingMode(waitingMode: Boolean) {
        if (waitingMode) {
            view.forecast_list.visibility = INVISIBLE
        }
        view.swipeRefreshLayout.isRefreshing = waitingMode
    }

    private fun initToolbar() {
        activity.setSupportActionBar(view.toolbar)
        val actionBar = activity.supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setIcon(R.mipmap.ic_launcher)
    }
}
