package eu.sesma.paraguas.navigation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import eu.sesma.paraguas.R
import eu.sesma.paraguas.domain.ForecastItem
import eu.sesma.paraguas.ui.detail.DetailActivity
import javax.inject.Inject


class Navigator
@Inject
constructor(
        val activity: AppCompatActivity
) {
    fun navigateToDetail(forecastItem: ForecastItem) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(activity.getString(R.string.item_key), forecastItem)
        activity.startActivity(intent)
    }
}