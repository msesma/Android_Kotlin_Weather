package com.paradigmadigital.paraguas.api

import javax.inject.Inject

class Endpoint
@Inject
constructor() {
    private val WUKEY: String = "93d0c442f87c0b10"
    var URL: String = "http://api.wunderground.com/api/$WUKEY/"
}

