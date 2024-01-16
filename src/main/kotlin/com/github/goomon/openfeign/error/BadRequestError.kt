package com.github.goomon.openfeign.error

data class BadRequestError(
    override val message: String?,
    override val code: BadRequestErrorCode,
    override val errors: List<StoreServiceError<Any>> = emptyList(),
    override val errorLevel: StoreServiceErrorLevel = StoreServiceErrorLevel.ERROR
) : StoreServiceError<BadRequestErrorCode>
