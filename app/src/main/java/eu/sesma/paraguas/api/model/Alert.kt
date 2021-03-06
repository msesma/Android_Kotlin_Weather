package eu.sesma.paraguas.api.model

import com.google.gson.annotations.SerializedName

/**
 * An alert object represents a severe weather warning issued for the requested location by a
 * governmental authority
 */
class Alert {

    @SerializedName(ModelConstants.FIELD_TITLE)
    val title: String? = null

    @SerializedName(ModelConstants.FIELD_REGIONS)
    val regions: List<String>? = null

    @SerializedName(ModelConstants.FIELD_SEVERITY)
    val severity: String? = null

    @SerializedName(ModelConstants.FIELD_DESCRIPTION)
    val description: String? = null

    @SerializedName(ModelConstants.FIELD_EXPIRES)
    val expires: String? = null

    @SerializedName(ModelConstants.FIELD_URI)
    val uri: String? = null

    @SerializedName(ModelConstants.FIELD_TIME)
    val time: String? = null
}
