package eu.sesma.paraguas.ui.detail

import eu.sesma.paraguas.domain.ForecastItem
import eu.sesma.paraguas.ui.master.MainActivityPresenter
import javax.inject.Inject

class DetailActivityPresenter
@Inject
constructor() {

    val TAG = MainActivityPresenter::class.simpleName

    private var decorator: DetailActivityUserInterface? = null


    fun initialize(decorator: DetailActivityUserInterface, forecastItem: ForecastItem?) {
        this.decorator = decorator
        this.decorator?.initialize(forecastItem)
    }

    fun dispose() {
        this.decorator = null
    }
}
