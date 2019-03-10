package eu.sesma.paraguas.platform

import java.util.*

fun Double.format(digits: Int) = java.lang.String.format(Locale.ENGLISH, "%.${digits}f", this)

