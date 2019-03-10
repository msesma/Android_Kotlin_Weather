package eu.sesma.paraguas.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * A data block object represents the various weather phenomena occurring over a period of time.
 */
class DataBlock {

    @SerializedName(ModelConstants.FIELD_SUMMARY)
    val summary: String? = null

    @SerializedName(ModelConstants.FIELD_ICON)
    val icon: String? = null

    @SerializedName(ModelConstants.FIELD_DATA)
    val dataPoints: ArrayList<DataPoint>? = null
}
