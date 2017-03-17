package com.paradigmadigital.paraguas.ui.detail

import android.os.Bundle
import com.paradigmadigital.paraguas.R
import com.paradigmadigital.paraguas.domain.ForecastItem
import com.paradigmadigital.paraguas.platform.BaseActivity
import javax.inject.Inject


class DetailActivity : BaseActivity() {

    @Inject
    lateinit var decorator: DetailActivityDecorator
    @Inject
    lateinit var presenter: DetailActivityPresenter

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_detail)
        activityComponent.inject(this)

        decorator.bind(getRootView())
        presenter.initialize(decorator, intent?.getSerializableExtra(getString(R.string.item_key)) as ForecastItem?)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }
}
