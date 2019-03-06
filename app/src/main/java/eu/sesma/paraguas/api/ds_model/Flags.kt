package eu.sesma.paraguas.api.ds_model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * The flags object contains various metadata information related to the request.
 */
class Flags {

    @SerializedName(ModelConstants.FIELD_DARKSKY_UNAVAILABLE)
    val darkSkyUnavailable: String? = null

    @SerializedName(ModelConstants.FIELD_DARKSKY_STATIONS)
    val darkSkyStations: ArrayList<String>? = null

    @SerializedName(ModelConstants.FIELD_DATAPOINT_STATIONS)
    val dataPointStations: ArrayList<String>? = null

    @SerializedName(ModelConstants.FIELD_ISD_STATIONS)
    val isdStations: ArrayList<String>? = null

    @SerializedName(ModelConstants.FIELD_LAMP_STATIONS)
    val lampStations: ArrayList<String>? = null

    @SerializedName(ModelConstants.FIELD_MADIS_STATIONS)
    val madisStations: ArrayList<String>? = null

    @SerializedName(ModelConstants.FIELD_METAR_STATIONS)
    val metarStations: ArrayList<String>? = null

    @SerializedName(ModelConstants.FIELD_METNO_LICENSE)
    val metnoLicense: String? = null

    @SerializedName(ModelConstants.FIELD_SOURCES)
    val sources: ArrayList<String>? = null

    @SerializedName(ModelConstants.FIELD_UNITS)
    val unit: Units? = null
}
