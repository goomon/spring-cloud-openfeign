package com.github.goomon.openfeign.error

interface StoreServiceError<T> {
    val message: String?
    val code: T
    val errors: List<StoreServiceError<Any>>
    val errorLevel: StoreServiceErrorLevel
}

enum class StoreServiceErrorLevel {
    INFO,
    WARN,
    ERROR,
    FATAL,
}