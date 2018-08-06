package eu.sesma.paraguas.domain.mappers


interface Mapper<OUT, IN> {
    abstract fun map(input: IN): OUT
}