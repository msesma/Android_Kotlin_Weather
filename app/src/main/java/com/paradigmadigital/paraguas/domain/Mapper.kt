package com.paradigmadigital.paraguas.domain


interface Mapper<OUT, IN> {
    abstract fun map(input: IN): OUT
}