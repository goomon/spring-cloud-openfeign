package com.github.goomon.openfeign.client.configuration

import feign.RetryableException
import feign.Retryer

// @Component
class CustomRetryer : Retryer {
    override fun clone(): Retryer {
        return CustomRetryer()
    }

    override fun continueOrPropagate(e: RetryableException?) {
        try {
            Thread.sleep(1_000)
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
            throw e
        }
    }
}
