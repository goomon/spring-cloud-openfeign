package com.github.goomon.openfeign.client.configuration

import feign.Logger
import feign.Request
import feign.codec.Encoder
import feign.form.spring.SpringFormEncoder
import feign.slf4j.Slf4jLogger
import org.springframework.beans.factory.ObjectFactory
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import java.util.concurrent.TimeUnit

class FeignConfiguration {
    @Bean
    fun feignFormEncoder(converters: ObjectFactory<HttpMessageConverters>): Encoder {
        return SpringFormEncoder(SpringEncoder(converters))
    }

    @Bean
    fun feignLoggerLeve(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean
    fun feignElapsedTimeoutExceededLogger(): Slf4jLogger {
        return FeignElapsedTimeoutExceededLogger(minElapsedTimeMillis = 10_000)
    }

    @Bean
    fun requestOption(): Request.Options {
        val connectionTimeout = 5L
        val readTimeout = 10L
        return Request.Options(
            connectionTimeout,
            TimeUnit.SECONDS,
            readTimeout,
            TimeUnit.SECONDS,
            true
        )
    }
}
