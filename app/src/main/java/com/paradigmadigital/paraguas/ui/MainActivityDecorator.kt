package com.paradigmadigital.paraguas.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.paradigmadigital.paraguas.R
import com.paradigmadigital.paraguas.api.ImageRepository
import com.paradigmadigital.paraguas.api.model.Astronomy
import com.paradigmadigital.paraguas.api.model.CurrentWeather
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.text.SimpleDateFormat
import javax.inject.Inject

class MainActivityDecorator
@Inject
constructor(val activity: AppCompatActivity, val imagerepo: ImageRepository) : MainActivityUserInterface {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar
    @BindView(R.id.icon)
    lateinit var icon: ImageView
    @BindView(R.id.condition)
    lateinit var tvcondition: TextView
    @BindView(R.id.temp)
    lateinit var tvtemp: TextView
    @BindView(R.id.feelslike)
    lateinit var tvfeelslike: TextView
    @BindView(R.id.daylight)
    lateinit var tvdaylight: TextView

    private var delegate: MainActivityUserInterface.Delegate? = null
    private var city: String? = null

    private val iconTarget = object : Target {

        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
            icon.setImageBitmap(bitmap)
        }

        override fun onBitmapFailed(errorDrawable: Drawable?) {

        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            Log.d("===", "onPrepareLoad")
        }
    }

    fun bind(view: View) {
        ButterKnife.bind(this, view)
        initToolbar()
    }

    fun dispose() {
        delegate = null
    }

    override fun initialize(delegate: MainActivityUserInterface.Delegate) {
        this.delegate = delegate
        toolbar.title = ""
    }

    override fun showError(errorMessage: String) {
        tvdaylight.setText(errorMessage)
    }

    override fun showCurrentWeather(currentWeather: CurrentWeather) {
        val url = currentWeather.iconUrl
        imagerepo.getCurrentIcon(url, iconTarget)
        tvcondition.setText(currentWeather.condition)
        tvtemp.setText("${currentWeather.temp} ºC")
        tvfeelslike.setText(String.format(activity.getString(R.string.feels_like), currentWeather.feelsLike))
    }

    override fun showCurrentAstronomy(astronomy: Astronomy) {
        val sunrise = SimpleDateFormat("HH:mm").format(astronomy.sunrise)
        val sunset = SimpleDateFormat("HH:mm").format(astronomy.sunset)
        tvdaylight . setText (String.format(activity.getString(R.string.daylight), sunrise, sunset))
    }

    override fun setCity(city: String) {
        this.city = city
        toolbar.title = city
    }

    private fun initToolbar() {
        activity.setSupportActionBar(toolbar)
        val actionBar = activity.supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setIcon(R.mipmap.ic_launcher)
    }
}
