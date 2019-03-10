package eu.sesma.paraguas.ui.master

import android.annotation.SuppressLint
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import eu.sesma.paraguas.R
import eu.sesma.paraguas.api.ImageRepository
import eu.sesma.paraguas.domain.Astronomy
import eu.sesma.paraguas.domain.CurrentWeather
import eu.sesma.paraguas.domain.ForecastItem
import java.text.SimpleDateFormat
import javax.inject.Inject

class MainActivityDecorator
@Inject
constructor(
    val activity: AppCompatActivity,
    val imagerepo: ImageRepository,
    val layoutManager: LinearLayoutManager,
    val adapter: ForecastAdapter,
    val graph: Graph
) : MainActivityUserInterface {

    private val TAG = MainActivityDecorator::class.simpleName

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar
    @BindView(R.id.icon)
    lateinit var icon: ImageView
    @BindView(R.id.graph)
    lateinit var graphView: ImageView
    @BindView(R.id.condition)
    lateinit var tvcondition: TextView
    @BindView(R.id.temp)
    lateinit var tvtemp: TextView
    @BindView(R.id.feelslike)
    lateinit var tvfeelslike: TextView
    @BindView(R.id.daylight)
    lateinit var tvdaylight: TextView
    @BindView(R.id.forecast_list)
    lateinit var list: RecyclerView
    @BindView(R.id.swipeRefreshLayout)
    lateinit var swipeRefresh: SwipeRefreshLayout

    private var delegate: MainActivityUserInterface.Delegate? = null
    private var city: String? = null

    private val forecastClickListener = object : ForecastClickListener {
        override fun onClick(index: Int) {
            delegate?.onClick(adapter.getItemAtPosition(index))
        }
    }

    internal var refreshListener: SwipeRefreshLayout.OnRefreshListener =
        SwipeRefreshLayout.OnRefreshListener { delegate?.onRefresh() }

    fun bind(view: View) {
        ButterKnife.bind(this, view)
        initToolbar()
        list.layoutManager = layoutManager
        list.itemAnimator = DefaultItemAnimator()
        swipeRefresh.setOnRefreshListener(refreshListener)
        setWaitingMode(true)
    }

    fun dispose() {
        delegate = null
    }

    override fun initialize(delegate: MainActivityUserInterface.Delegate) {
        this.delegate = delegate
        toolbar.title = ""
        list.adapter = adapter
        adapter.setClickListener(forecastClickListener)
    }

    override fun showError(error: Exception) {
        activity.runOnUiThread {
            setWaitingMode(false)
            Log.e(TAG, error.toString())
            tvtemp.text = activity.getString(R.string.connection_error)
        }
    }

    override fun showCurrentWeather(currentWeather: CurrentWeather) {
        setWaitingMode(false)
        icon.setImageResource(imagerepo.getCurrentIcon(currentWeather.iconName))
        tvcondition.text = currentWeather.condition
        tvtemp.text = String.format(activity.getString(R.string.number), currentWeather.temp)
        tvfeelslike.text = String.format(activity.getString(R.string.feels_like), currentWeather.feelsLike)
        graph.currentWeather = currentWeather
        graph.draw(graphView);
    }

    @SuppressLint("SimpleDateFormat")
    override fun showCurrentAstronomy(astronomy: Astronomy) {
        setWaitingMode(false)
        val sunrise = SimpleDateFormat("HH:mm").format(astronomy.sunrise)
        val sunset = SimpleDateFormat("HH:mm").format(astronomy.sunset)
        tvdaylight.text = String.format(activity.getString(R.string.daylight), sunrise, sunset)
        graph.astronomy = astronomy
        graph.draw(graphView);
    }

    override fun showForecast(forecast: List<ForecastItem>) {
        setWaitingMode(false)
        list.visibility = if (forecast.isEmpty()) INVISIBLE else VISIBLE
        adapter.swap(forecast)
        graph.forecast = forecast
        graph.draw(graphView);
    }

    override fun setCity(city: String) {
        this.city = city
        toolbar.title = city
    }

    private fun setWaitingMode(waitingMode: Boolean) {
        if (waitingMode) {
            list.visibility = INVISIBLE
        }
        swipeRefresh.isRefreshing = waitingMode
    }

    private fun initToolbar() {
        activity.setSupportActionBar(toolbar)
        val actionBar = activity.supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setIcon(R.mipmap.ic_launcher)
    }
}
