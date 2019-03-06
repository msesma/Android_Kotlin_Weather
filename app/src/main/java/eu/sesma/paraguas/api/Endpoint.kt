package eu.sesma.paraguas.api

import android.content.Context
import eu.sesma.paraguas.R
import javax.inject.Inject

class Endpoint
@Inject
constructor(context: Context) {
    private val wuKey: String = context.getString(R.string.wu_api_token)
    var url: String = "http://api.wunderground.com/api/$wuKey/"
}

