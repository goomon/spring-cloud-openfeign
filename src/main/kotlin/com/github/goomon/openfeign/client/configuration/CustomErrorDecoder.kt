package com.github.goomon.openfeign.client.configuration

import feign.FeignException
import feign.Request
import feign.Response
import feign.RetryableException
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class CustomErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String, response: Response): Exception {
        if (response.status() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return RetryableException(HttpStatus.OK.value(), "5XX error", Request.HttpMethod.GET, null, response.request())
        }

        return FeignException.errorStatus(methodKey, response)
    }
}
