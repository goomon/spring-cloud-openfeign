package com.github.goomon.openfeign.configuration

import com.github.goomon.openfeign.error.BadRequestError
import com.github.goomon.openfeign.error.BadRequestErrorCode
import com.github.goomon.openfeign.error.StoreServiceError
import com.github.goomon.openfeign.excpetion.StoreClientException
import com.github.goomon.openfeign.excpetion.StoreServerException
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
class ControllerAdvice {
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = [StoreServerException::class])
    @ResponseBody
    fun handleUnexpectedException(
        request: HttpServletRequest,
        e: StoreServerException,
    ): StoreServiceError<BadRequestErrorCode> {
        return BadRequestError(
            message = "This is server error.",
            code = BadRequestErrorCode.UNEXPECTED_ERROR,
        )
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [StoreClientException::class])
    @ResponseBody
    fun handleClientException(
        request: HttpServletRequest,
        e: StoreClientException,
    ): StoreServiceError<BadRequestErrorCode> {
        return BadRequestError(
            message = "This is client error",
            code = BadRequestErrorCode.UNEXPECTED_ERROR,
        )
    }

    @ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(value = [IOException::class])
    @ResponseBody
    fun handleIOException(
        request: HttpServletRequest,
        e: IOException,
    ): StoreServiceError<BadRequestErrorCode> {
        return BadRequestError(
            message = "This is client error",
            code = BadRequestErrorCode.UNEXPECTED_ERROR,
        )
    }
}