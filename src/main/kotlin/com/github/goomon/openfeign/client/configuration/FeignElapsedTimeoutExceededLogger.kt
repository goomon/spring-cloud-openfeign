package com.github.goomon.openfeign.client.configuration

import feign.Response
import feign.slf4j.Slf4jLogger
import java.io.IOException
import org.slf4j.LoggerFactory

class FeignElapsedTimeoutExceededLogger(
    private val minElapsedTimeMillis: Long,
) : Slf4jLogger() {

    private val LOGGER = LoggerFactory.getLogger(javaClass)

    override fun logAndRebufferResponse(
        configKey: String,
        logLevel: Level,
        response: Response,
        elapsedTime: Long,
    ): Response {
        if (minElapsedTimeMillis < elapsedTime) {
            LOGGER.info("[FeignElapsedTimeoutExceededLogger] method: ${methodTag((configKey).trim())}, status: ${response.status()}, elapsedTime: $elapsedTime")
        }
        return super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime)
    }

    override fun logIOException(
        configKey: String,
        logLevel: Level,
        ioe: IOException,
        elapsedTime: Long,
    ): IOException {
        if (minElapsedTimeMillis < elapsedTime) {
            LOGGER.info("[FeignElapsedTimeoutExceededLogger] method: ${methodTag((configKey).trim())}, ioe: ${ioe.message}, elapsedTime: $elapsedTime")
        }
        return super.logIOException(configKey, logLevel, ioe, elapsedTime)
    }
}
