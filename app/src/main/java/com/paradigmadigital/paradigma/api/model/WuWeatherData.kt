package com.paradigmadigital.paradigma.api.model

import com.google.gson.annotations.SerializedName

class WuWeatherData {

    @SerializedName("response")
    val response: Response? = null

    @SerializedName("hourly_forecast")
    val hourlyForecast: List<HourlyForecast>? = null

}

// {
// "response": {
// "version":"0.1",
// "termsofService":"http://www.wunderground.com/weather/api/d/terms.html",
// "features": {
// "hourly": 1
// }
// }
// ,
// "hourly_forecast": [
// {
// "FCTTIME": {
// "hour": "13","hour_padded": "13","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "20","mday_padded": "20","yday": "323","isdst": "0","epoch": "1384948800","pretty":
// "1:00 PM CET on November 20, 2013","civil": "1:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Wednesday","weekday_name_night": "Wednesday Night","weekday_name_abbrev":
// "Wed","weekday_name_unlang": "Wednesday","weekday_name_night_unlang": "Wednesday Night","ampm": "PM","tz": "","age":
// ""
// },
// "temp": {"english": "45", "metric": "7"},
// "dewpoint": {"english": "27", "metric": "-2"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/clear.gif",
// "fctcode": "1",
// "sky": "13",
// "wspd": {"english": "7", "metric": "11"},
// "wdir": {"dir": "NW", "degrees": "308"},
// "wx": "",
// "uvi": "3",
// "humidity": "50",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "45", "metric": "7"},
// "qpf": {"english": "", "metric": "0"},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.04", "metric": "1017"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "14","hour_padded": "14","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "20","mday_padded": "20","yday": "323","isdst": "0","epoch": "1384952400","pretty":
// "2:00 PM CET on November 20, 2013","civil": "2:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Wednesday","weekday_name_night": "Wednesday Night","weekday_name_abbrev":
// "Wed","weekday_name_unlang": "Wednesday","weekday_name_night_unlang": "Wednesday Night","ampm": "PM","tz": "","age":
// ""
// },
// "temp": {"english": "47", "metric": "8"},
// "dewpoint": {"english": "26", "metric": "-2"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/clear.gif",
// "fctcode": "1",
// "sky": "13",
// "wspd": {"english": "8", "metric": "12"},
// "wdir": {"dir": "NW", "degrees": "308"},
// "wx": "",
// "uvi": "3",
// "humidity": "46",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "47", "metric": "8"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.04", "metric": "1017"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "15","hour_padded": "15","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "20","mday_padded": "20","yday": "323","isdst": "0","epoch": "1384956000","pretty":
// "3:00 PM CET on November 20, 2013","civil": "3:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Wednesday","weekday_name_night": "Wednesday Night","weekday_name_abbrev":
// "Wed","weekday_name_unlang": "Wednesday","weekday_name_night_unlang": "Wednesday Night","ampm": "PM","tz": "","age":
// ""
// },
// "temp": {"english": "48", "metric": "9"},
// "dewpoint": {"english": "26", "metric": "-3"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/clear.gif",
// "fctcode": "1",
// "sky": "13",
// "wspd": {"english": "8", "metric": "13"},
// "wdir": {"dir": "NW", "degrees": "308"},
// "wx": "",
// "uvi": "3",
// "humidity": "42",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "48", "metric": "9"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.04", "metric": "1017"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "16","hour_padded": "16","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "20","mday_padded": "20","yday": "323","isdst": "0","epoch": "1384959600","pretty":
// "4:00 PM CET on November 20, 2013","civil": "4:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Wednesday","weekday_name_night": "Wednesday Night","weekday_name_abbrev":
// "Wed","weekday_name_unlang": "Wednesday","weekday_name_night_unlang": "Wednesday Night","ampm": "PM","tz": "","age":
// ""
// },
// "temp": {"english": "50", "metric": "10"},
// "dewpoint": {"english": "25", "metric": "-3"},
// "condition": "Partly Cloudy",
// "icon": "partlycloudy",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/partlycloudy.gif",
// "fctcode": "2",
// "sky": "63",
// "wspd": {"english": "9", "metric": "14"},
// "wdir": {"dir": "NW", "degrees": "311"},
// "wx": "",
// "uvi": "1",
// "humidity": "38",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "50", "metric": "10"},
// "qpf": {"english": "", "metric": "0"},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.04", "metric": "1017"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "17","hour_padded": "17","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "20","mday_padded": "20","yday": "323","isdst": "0","epoch": "1384963200","pretty":
// "5:00 PM CET on November 20, 2013","civil": "5:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Wednesday","weekday_name_night": "Wednesday Night","weekday_name_abbrev":
// "Wed","weekday_name_unlang": "Wednesday","weekday_name_night_unlang": "Wednesday Night","ampm": "PM","tz": "","age":
// ""
// },
// "temp": {"english": "48", "metric": "9"},
// "dewpoint": {"english": "26", "metric": "-3"},
// "condition": "Partly Cloudy",
// "icon": "partlycloudy",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/partlycloudy.gif",
// "fctcode": "2",
// "sky": "63",
// "wspd": {"english": "8", "metric": "13"},
// "wdir": {"dir": "NW", "degrees": "311"},
// "wx": "",
// "uvi": "1",
// "humidity": "42",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "48", "metric": "9"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.04", "metric": "1017"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "18","hour_padded": "18","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "20","mday_padded": "20","yday": "323","isdst": "0","epoch": "1384966800","pretty":
// "6:00 PM CET on November 20, 2013","civil": "6:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Wednesday","weekday_name_night": "Wednesday Night","weekday_name_abbrev":
// "Wed","weekday_name_unlang": "Wednesday","weekday_name_night_unlang": "Wednesday Night","ampm": "PM","tz": "","age":
// ""
// },
// "temp": {"english": "45", "metric": "7"},
// "dewpoint": {"english": "26", "metric": "-2"},
// "condition": "Partly Cloudy",
// "icon": "partlycloudy",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_partlycloudy.gif",
// "fctcode": "2",
// "sky": "63",
// "wspd": {"english": "8", "metric": "12"},
// "wdir": {"dir": "NW", "degrees": "311"},
// "wx": "",
// "uvi": "1",
// "humidity": "47",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "45", "metric": "7"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.04", "metric": "1017"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "19","hour_padded": "19","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "20","mday_padded": "20","yday": "323","isdst": "0","epoch": "1384970400","pretty":
// "7:00 PM CET on November 20, 2013","civil": "7:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Wednesday","weekday_name_night": "Wednesday Night","weekday_name_abbrev":
// "Wed","weekday_name_unlang": "Wednesday","weekday_name_night_unlang": "Wednesday Night","ampm": "PM","tz": "","age":
// ""
// },
// "temp": {"english": "43", "metric": "6"},
// "dewpoint": {"english": "27", "metric": "-2"},
// "condition": "Partly Cloudy",
// "icon": "partlycloudy",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_partlycloudy.gif",
// "fctcode": "2",
// "sky": "45",
// "wspd": {"english": "7", "metric": "11"},
// "wdir": {"dir": "NW", "degrees": "312"},
// "wx": "",
// "uvi": "0",
// "humidity": "51",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "43", "metric": "6"},
// "qpf": {"english": "", "metric": "0"},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.07", "metric": "1018"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "20","hour_padded": "20","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "20","mday_padded": "20","yday": "323","isdst": "0","epoch": "1384974000","pretty":
// "8:00 PM CET on November 20, 2013","civil": "8:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Wednesday","weekday_name_night": "Wednesday Night","weekday_name_abbrev":
// "Wed","weekday_name_unlang": "Wednesday","weekday_name_night_unlang": "Wednesday Night","ampm": "PM","tz": "","age":
// ""
// },
// "temp": {"english": "42", "metric": "5"},
// "dewpoint": {"english": "26", "metric": "-2"},
// "condition": "Partly Cloudy",
// "icon": "partlycloudy",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_partlycloudy.gif",
// "fctcode": "2",
// "sky": "45",
// "wspd": {"english": "6", "metric": "10"},
// "wdir": {"dir": "NW", "degrees": "312"},
// "wx": "",
// "uvi": "0",
// "humidity": "53",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "42", "metric": "5"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.07", "metric": "1018"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "21","hour_padded": "21","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "20","mday_padded": "20","yday": "323","isdst": "0","epoch": "1384977600","pretty":
// "9:00 PM CET on November 20, 2013","civil": "9:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Wednesday","weekday_name_night": "Wednesday Night","weekday_name_abbrev":
// "Wed","weekday_name_unlang": "Wednesday","weekday_name_night_unlang": "Wednesday Night","ampm": "PM","tz": "","age":
// ""
// },
// "temp": {"english": "40", "metric": "5"},
// "dewpoint": {"english": "26", "metric": "-3"},
// "condition": "Partly Cloudy",
// "icon": "partlycloudy",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_partlycloudy.gif",
// "fctcode": "2",
// "sky": "45",
// "wspd": {"english": "6", "metric": "9"},
// "wdir": {"dir": "NW", "degrees": "312"},
// "wx": "",
// "uvi": "0",
// "humidity": "54",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "40", "metric": "5"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.07", "metric": "1018"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "22","hour_padded": "22","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "20","mday_padded": "20","yday": "323","isdst": "0","epoch": "1384981200","pretty":
// "10:00 PM CET on November 20, 2013","civil": "10:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Wednesday","weekday_name_night": "Wednesday Night","weekday_name_abbrev":
// "Wed","weekday_name_unlang": "Wednesday","weekday_name_night_unlang": "Wednesday Night","ampm": "PM","tz": "","age":
// ""
// },
// "temp": {"english": "39", "metric": "4"},
// "dewpoint": {"english": "25", "metric": "-3"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "2",
// "wspd": {"english": "5", "metric": "8"},
// "wdir": {"dir": "NW", "degrees": "308"},
// "wx": "",
// "uvi": "0",
// "humidity": "56",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "39", "metric": "4"},
// "qpf": {"english": "", "metric": "0"},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.08", "metric": "1018"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "23","hour_padded": "23","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "20","mday_padded": "20","yday": "323","isdst": "0","epoch": "1384984800","pretty":
// "11:00 PM CET on November 20, 2013","civil": "11:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Wednesday","weekday_name_night": "Wednesday Night","weekday_name_abbrev":
// "Wed","weekday_name_unlang": "Wednesday","weekday_name_night_unlang": "Wednesday Night","ampm": "PM","tz": "","age":
// ""
// },
// "temp": {"english": "38", "metric": "3"},
// "dewpoint": {"english": "25", "metric": "-3"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "2",
// "wspd": {"english": "5", "metric": "7"},
// "wdir": {"dir": "NW", "degrees": "308"},
// "wx": "",
// "uvi": "0",
// "humidity": "58",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "38", "metric": "3"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.08", "metric": "1018"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "0","hour_padded": "00","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1384988400","pretty":
// "12:00 AM CET on November 21, 2013","civil": "12:00 AM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "AM","tz": "","age": ""
// },
// "temp": {"english": "37", "metric": "3"},
// "dewpoint": {"english": "25", "metric": "-3"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "2",
// "wspd": {"english": "4", "metric": "7"},
// "wdir": {"dir": "NW", "degrees": "308"},
// "wx": "",
// "uvi": "0",
// "humidity": "61",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "37", "metric": "3"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.08", "metric": "1018"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "1","hour_padded": "01","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1384992000","pretty":
// "1:00 AM CET on November 21, 2013","civil": "1:00 AM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "AM","tz": "","age": ""
// },
// "temp": {"english": "36", "metric": "2"},
// "dewpoint": {"english": "25", "metric": "-3"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "4", "metric": "6"},
// "wdir": {"dir": "WNW", "degrees": "297"},
// "wx": "",
// "uvi": "0",
// "humidity": "63",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "36", "metric": "2"},
// "qpf": {"english": "", "metric": "0"},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.04", "metric": "1017"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "2","hour_padded": "02","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1384995600","pretty":
// "2:00 AM CET on November 21, 2013","civil": "2:00 AM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "AM","tz": "","age": ""
// },
// "temp": {"english": "35", "metric": "1"},
// "dewpoint": {"english": "24", "metric": "-3"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "4", "metric": "7"},
// "wdir": {"dir": "WNW", "degrees": "297"},
// "wx": "",
// "uvi": "0",
// "humidity": "64",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "35", "metric": "1"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.04", "metric": "1017"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "3","hour_padded": "03","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1384999200","pretty":
// "3:00 AM CET on November 21, 2013","civil": "3:00 AM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "AM","tz": "","age": ""
// },
// "temp": {"english": "33", "metric": "1"},
// "dewpoint": {"english": "24", "metric": "-4"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "5", "metric": "7"},
// "wdir": {"dir": "WNW", "degrees": "297"},
// "wx": "",
// "uvi": "0",
// "humidity": "66",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "33", "metric": "1"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.04", "metric": "1017"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "4","hour_padded": "04","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385002800","pretty":
// "4:00 AM CET on November 21, 2013","civil": "4:00 AM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "AM","tz": "","age": ""
// },
// "temp": {"english": "32", "metric": "0"},
// "dewpoint": {"english": "23", "metric": "-4"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "5", "metric": "8"},
// "wdir": {"dir": "West", "degrees": "277"},
// "wx": "",
// "uvi": "0",
// "humidity": "67",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "32", "metric": "0"},
// "qpf": {"english": "", "metric": "0"},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.01", "metric": "1016"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "5","hour_padded": "05","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385006400","pretty":
// "5:00 AM CET on November 21, 2013","civil": "5:00 AM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "AM","tz": "","age": ""
// },
// "temp": {"english": "31", "metric": "0"},
// "dewpoint": {"english": "22", "metric": "-4"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "5", "metric": "7"},
// "wdir": {"dir": "West", "degrees": "277"},
// "wx": "",
// "uvi": "0",
// "humidity": "68",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "31", "metric": "0"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.01", "metric": "1016"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "6","hour_padded": "06","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385010000","pretty":
// "6:00 AM CET on November 21, 2013","civil": "6:00 AM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "AM","tz": "","age": ""
// },
// "temp": {"english": "31", "metric": "0"},
// "dewpoint": {"english": "22", "metric": "-5"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "4", "metric": "7"},
// "wdir": {"dir": "West", "degrees": "277"},
// "wx": "",
// "uvi": "0",
// "humidity": "68",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "31", "metric": "0"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "30.01", "metric": "1016"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "7","hour_padded": "07","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385013600","pretty":
// "7:00 AM CET on November 21, 2013","civil": "7:00 AM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "AM","tz": "","age": ""
// },
// "temp": {"english": "30", "metric": "0"},
// "dewpoint": {"english": "21", "metric": "-5"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "4", "metric": "6"},
// "wdir": {"dir": "West", "degrees": "274"},
// "wx": "",
// "uvi": "0",
// "humidity": "69",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "30", "metric": "0"},
// "qpf": {"english": "", "metric": "0"},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.94", "metric": "1013"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "8","hour_padded": "08","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385017200","pretty":
// "8:00 AM CET on November 21, 2013","civil": "8:00 AM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "AM","tz": "","age": ""
// },
// "temp": {"english": "33", "metric": "1"},
// "dewpoint": {"english": "22", "metric": "-5"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "4", "metric": "7"},
// "wdir": {"dir": "West", "degrees": "274"},
// "wx": "",
// "uvi": "0",
// "humidity": "63",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "33", "metric": "1"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.94", "metric": "1013"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "9","hour_padded": "09","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385020800","pretty":
// "9:00 AM CET on November 21, 2013","civil": "9:00 AM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "AM","tz": "","age": ""
// },
// "temp": {"english": "36", "metric": "2"},
// "dewpoint": {"english": "22", "metric": "-4"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "5", "metric": "7"},
// "wdir": {"dir": "West", "degrees": "274"},
// "wx": "",
// "uvi": "0",
// "humidity": "58",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "36", "metric": "2"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.94", "metric": "1013"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "10","hour_padded": "10","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385024400","pretty":
// "10:00 AM CET on November 21, 2013","civil": "10:00 AM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "AM","tz": "","age": ""
// },
// "temp": {"english": "39", "metric": "4"},
// "dewpoint": {"english": "23", "metric": "-4"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "5", "metric": "8"},
// "wdir": {"dir": "West", "degrees": "267"},
// "wx": "",
// "uvi": "1",
// "humidity": "52",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "39", "metric": "4"},
// "qpf": {"english": "", "metric": "0"},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.95", "metric": "1014"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "11","hour_padded": "11","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385028000","pretty":
// "11:00 AM CET on November 21, 2013","civil": "11:00 AM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "AM","tz": "","age": ""
// },
// "temp": {"english": "42", "metric": "6"},
// "dewpoint": {"english": "23", "metric": "-4"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "7", "metric": "11"},
// "wdir": {"dir": "West", "degrees": "267"},
// "wx": "",
// "uvi": "1",
// "humidity": "46",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "42", "metric": "6"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.95", "metric": "1014"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "12","hour_padded": "12","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385031600","pretty":
// "12:00 PM CET on November 21, 2013","civil": "12:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "PM","tz": "","age": ""
// },
// "temp": {"english": "45", "metric": "7"},
// "dewpoint": {"english": "23", "metric": "-4"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "9", "metric": "15"},
// "wdir": {"dir": "West", "degrees": "267"},
// "wx": "",
// "uvi": "1",
// "humidity": "41",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "45", "metric": "7"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.95", "metric": "1014"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "13","hour_padded": "13","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385035200","pretty":
// "1:00 PM CET on November 21, 2013","civil": "1:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "PM","tz": "","age": ""
// },
// "temp": {"english": "48", "metric": "9"},
// "dewpoint": {"english": "23", "metric": "-4"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/clear.gif",
// "fctcode": "1",
// "sky": "1",
// "wspd": {"english": "11", "metric": "18"},
// "wdir": {"dir": "WSW", "degrees": "258"},
// "wx": "",
// "uvi": "3",
// "humidity": "35",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "48", "metric": "9"},
// "qpf": {"english": "", "metric": "0"},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.87", "metric": "1011"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "14","hour_padded": "14","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385038800","pretty":
// "2:00 PM CET on November 21, 2013","civil": "2:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "PM","tz": "","age": ""
// },
// "temp": {"english": "49", "metric": "9"},
// "dewpoint": {"english": "23", "metric": "-4"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/clear.gif",
// "fctcode": "1",
// "sky": "1",
// "wspd": {"english": "11", "metric": "18"},
// "wdir": {"dir": "WSW", "degrees": "258"},
// "wx": "",
// "uvi": "3",
// "humidity": "34",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "49", "metric": "9"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.87", "metric": "1011"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "15","hour_padded": "15","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385042400","pretty":
// "3:00 PM CET on November 21, 2013","civil": "3:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "PM","tz": "","age": ""
// },
// "temp": {"english": "49", "metric": "10"},
// "dewpoint": {"english": "23", "metric": "-4"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/clear.gif",
// "fctcode": "1",
// "sky": "1",
// "wspd": {"english": "12", "metric": "19"},
// "wdir": {"dir": "WSW", "degrees": "258"},
// "wx": "",
// "uvi": "3",
// "humidity": "33",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "49", "metric": "10"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.87", "metric": "1011"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "16","hour_padded": "16","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385046000","pretty":
// "4:00 PM CET on November 21, 2013","civil": "4:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "PM","tz": "","age": ""
// },
// "temp": {"english": "50", "metric": "10"},
// "dewpoint": {"english": "23", "metric": "-4"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/clear.gif",
// "fctcode": "1",
// "sky": "2",
// "wspd": {"english": "12", "metric": "19"},
// "wdir": {"dir": "West", "degrees": "266"},
// "wx": "",
// "uvi": "1",
// "humidity": "32",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "50", "metric": "10"},
// "qpf": {"english": "", "metric": "0"},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.79", "metric": "1008"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "17","hour_padded": "17","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385049600","pretty":
// "5:00 PM CET on November 21, 2013","civil": "5:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "PM","tz": "","age": ""
// },
// "temp": {"english": "48", "metric": "9"},
// "dewpoint": {"english": "24", "metric": "-4"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/clear.gif",
// "fctcode": "1",
// "sky": "2",
// "wspd": {"english": "11", "metric": "17"},
// "wdir": {"dir": "West", "degrees": "266"},
// "wx": "",
// "uvi": "1",
// "humidity": "37",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "48", "metric": "9"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.79", "metric": "1008"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "18","hour_padded": "18","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385053200","pretty":
// "6:00 PM CET on November 21, 2013","civil": "6:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "PM","tz": "","age": ""
// },
// "temp": {"english": "47", "metric": "8"},
// "dewpoint": {"english": "24", "metric": "-3"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "2",
// "wspd": {"english": "9", "metric": "15"},
// "wdir": {"dir": "West", "degrees": "266"},
// "wx": "",
// "uvi": "1",
// "humidity": "41",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "47", "metric": "8"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.79", "metric": "1008"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "19","hour_padded": "19","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385056800","pretty":
// "7:00 PM CET on November 21, 2013","civil": "7:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "PM","tz": "","age": ""
// },
// "temp": {"english": "45", "metric": "7"},
// "dewpoint": {"english": "25", "metric": "-3"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "8", "metric": "13"},
// "wdir": {"dir": "West", "degrees": "280"},
// "wx": "",
// "uvi": "0",
// "humidity": "46",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "45", "metric": "7"},
// "qpf": {"english": "", "metric": "0"},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.79", "metric": "1008"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "20","hour_padded": "20","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385060400","pretty":
// "8:00 PM CET on November 21, 2013","civil": "8:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "PM","tz": "","age": ""
// },
// "temp": {"english": "44", "metric": "6"},
// "dewpoint": {"english": "26", "metric": "-3"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "8", "metric": "12"},
// "wdir": {"dir": "West", "degrees": "280"},
// "wx": "",
// "uvi": "0",
// "humidity": "49",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "44", "metric": "6"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.79", "metric": "1008"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "21","hour_padded": "21","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385064000","pretty":
// "9:00 PM CET on November 21, 2013","civil": "9:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "PM","tz": "","age": ""
// },
// "temp": {"english": "42", "metric": "6"},
// "dewpoint": {"english": "26", "metric": "-2"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "0",
// "wspd": {"english": "7", "metric": "12"},
// "wdir": {"dir": "West", "degrees": "280"},
// "wx": "",
// "uvi": "0",
// "humidity": "52",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "42", "metric": "6"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.79", "metric": "1008"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "22","hour_padded": "22","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385067600","pretty":
// "10:00 PM CET on November 21, 2013","civil": "10:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "PM","tz": "","age": ""
// },
// "temp": {"english": "41", "metric": "5"},
// "dewpoint": {"english": "27", "metric": "-2"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "14",
// "wspd": {"english": "7", "metric": "11"},
// "wdir": {"dir": "WNW", "degrees": "285"},
// "wx": "",
// "uvi": "0",
// "humidity": "55",
// "windchill": {"english": "-9998", "metric": "-9998"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "41", "metric": "5"},
// "qpf": {"english": "", "metric": "0"},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.81", "metric": "1009"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "23","hour_padded": "23","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "21","mday_padded": "21","yday": "324","isdst": "0","epoch": "1385071200","pretty":
// "11:00 PM CET on November 21, 2013","civil": "11:00 PM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Thursday","weekday_name_night": "Thursday Night","weekday_name_abbrev":
// "Thu","weekday_name_unlang": "Thursday","weekday_name_night_unlang": "Thursday Night","ampm": "PM","tz": "","age": ""
// },
// "temp": {"english": "40", "metric": "4"},
// "dewpoint": {"english": "27", "metric": "-2"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "14",
// "wspd": {"english": "7", "metric": "11"},
// "wdir": {"dir": "WNW", "degrees": "285"},
// "wx": "",
// "uvi": "0",
// "humidity": "60",
// "windchill": {"english": "-6654", "metric": "-6665"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "40", "metric": "4"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.81", "metric": "1009"}
// }
// ,
// {
// "FCTTIME": {
// "hour": "0","hour_padded": "00","min": "00","sec": "0","year": "2013","mon": "11","mon_padded": "11","mon_abbrev":
// "Nov","mday": "22","mday_padded": "22","yday": "325","isdst": "0","epoch": "1385074800","pretty":
// "12:00 AM CET on November 22, 2013","civil": "12:00 AM","month_name": "November","month_name_abbrev":
// "Nov","weekday_name": "Friday","weekday_name_night": "Friday Night","weekday_name_abbrev":
// "Fri","weekday_name_unlang": "Friday","weekday_name_night_unlang": "Friday Night","ampm": "AM","tz": "","age": ""
// },
// "temp": {"english": "38", "metric": "4"},
// "dewpoint": {"english": "28", "metric": "-1"},
// "condition": "Clear",
// "icon": "clear",
// "icon_url":"http://icons-ak.wxug.com/i/c/k/nt_clear.gif",
// "fctcode": "1",
// "sky": "14",
// "wspd": {"english": "6", "metric": "10"},
// "wdir": {"dir": "WNW", "degrees": "285"},
// "wx": "",
// "uvi": "0",
// "humidity": "64",
// "windchill": {"english": "-3311", "metric": "-3332"},
// "heatindex": {"english": "-9998", "metric": "-9998"},
// "feelslike": {"english": "38", "metric": "4"},
// "qpf": {"english": "", "metric": ""},
// "snow": {"english": "", "metric": ""},
// "pop": "0",
// "mslp": {"english": "29.81", "metric": "1009"}
// }
// ]
// }
