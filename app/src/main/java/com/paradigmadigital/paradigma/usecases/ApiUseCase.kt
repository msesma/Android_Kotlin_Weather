package com.paradigmadigital.paradigma.usecases


interface ApiUseCase {
    companion object {
        val WUKEY = "93d0c442f87c0b10"
        val URL = "http://api.wunderground.com/api/${WUKEY}/"
    }
}