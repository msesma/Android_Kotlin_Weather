package com.paradigmadigital.paraguas.navigation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.paradigmadigital.paraguas.R
import com.paradigmadigital.paraguas.domain.ForecastItem
import com.paradigmadigital.paraguas.ui.detail.DetailActivity
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