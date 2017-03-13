package com.paradigmadigital.paraguas.api.model

import com.google.gson.annotations.SerializedName

class FctTime {

    @SerializedName("hour")
    val hour: String? = null

    @SerializedName("epoch")
    val epoch: String? = null

    @SerializedName("weekday_name_abbrev")
    val weekdayNameAbbrev: String? = null
}
// "FCTTIME": {
// "hour": "13",
// "hour_padded": "13",
// "min": "00",
// "sec": "0",
// "year": "2013",
// "mon": "11",
// "mon_padded": "11",
// "mon_abbrev": "Nov",
// "mday": "20",
// "mday_padded": "20",
// "yday": "323",
// "isdst": "0",
// "epoch": "1384948800",
// "pretty": "1:00 PM CET on November 20, 2013",
// "civil": "1:00 PM",
// "month_name": "November",
// "month_name_abbrev": "Nov",
// "weekday_name": "Wednesday",
// "weekday_name_night": "Wednesday Night",
// "weekday_name_abbrev": "Wed",
// "weekday_name_unlang": "Wednesday",
// "weekday_name_night_unlang": "Wednesday Night",
// "ampm": "PM",
// "tz": "",
// "age": ""
// },
