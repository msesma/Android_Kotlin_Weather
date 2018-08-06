package eu.sesma.paraguas.ui.detail

import android.os.Bundle
import eu.sesma.paraguas.R
import eu.sesma.paraguas.domain.ForecastItem
import eu.sesma.paraguas.platform.BaseActivity
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
