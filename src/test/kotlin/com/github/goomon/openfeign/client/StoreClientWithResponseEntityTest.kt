package com.github.goomon.openfeign.client

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus.OK

@SpringBootTest
class StoreClientWithResponseEntityTest {
    @Autowired
    private lateinit var client: StoreClientWithResponseEntity

    @Test
    fun getStores() {
        // give
        // when
        val responseEntity = client.getStores()

        // then
        responseEntity.statusCode shouldBe OK
        responseEntity.body?.size shouldBe 3
    }

    @Test
    fun triggerServerError() {
        // given
        // when
        val responseEntity = client.triggerServerError()

        // then
        responseEntity.statusCode.is5xxServerError shouldBe true
    }

    @Test
    fun triggerClientError() {
        // given
        // when
        val responseEntity = client.triggerClientError()

        // then
        responseEntity.statusCode.is4xxClientError shouldBe true
    }
}
