package eu.sesma.paraguas.api

object Constants {

    val OPTIONS_LANGUAGE = "lang"
    val OPTIONS_UNIT = "units"
    val OPTIONS_EXTEND = "extend"
    val OPTIONS_EXTEND_HOURLY = "hourly"
    val OPTIONS_EXCLUDE = "exclude"
    val OPTIONS_EXCLUDE_CURRENLY = "currently"
    val OPTIONS_EXCLUDE_MINUTELY = "minutely"
    val OPTIONS_EXCLUDE_HOURLY = "hourly"
    val OPTIONS_EXCLUDE_DAILY = "daily"
    val OPTIONS_EXCLUDE_ALERTS = "alerts"
    val OPTIONS_EXCLUDE_FLAGS = "flags"

    val DEFAULT_CACHE_AGE = 60 * 60 * 6 // 6 hours
    val DEFAULT_CACHE_SIZE = 5 * 1024 * 1024 // 5MB;
    val DEFAULT_CONNECTION_TIMEOUT = 60 // seconds
}
