package com.paradigmadigital.paradigma.domain


interface Mapper<OUT, IN> {
    abstract fun map(input: IN): OUT
}