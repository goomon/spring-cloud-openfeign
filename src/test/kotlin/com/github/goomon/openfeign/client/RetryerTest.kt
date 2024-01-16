package com.github.goomon.openfeign.client

import com.github.goomon.openfeign.client.configuration.CustomErrorDecoder
import feign.Feign
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RetryerTest {

    @Test
    fun defaultRetryerTest() {
        val client = Feign.builder()
            .errorDecoder(CustomErrorDecoder())
            .target(StoreClientWithResponseEntity::class.java, "http://localhost:9090")

        client.triggerServerError()
    }
}
