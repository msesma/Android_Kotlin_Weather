package eu.sesma.paraguas.api

import android.content.Context
import eu.sesma.paraguas.R
import javax.inject.Inject

class Endpoint
@Inject
constructor(context: Context) {
    private val dsKey: String = context.getString(R.string.DarkSky_api_token)
    var url: String = "https://api.darksky.net/forecast/$dsKey/"
}

