package com.paradigmadigital.paraguas.platform

fun Float.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

