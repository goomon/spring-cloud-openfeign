package com.github.goomon.openfeign.client

import com.github.goomon.openfeign.domain.model.Store
import feign.FeignException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StoreClientTest {
    @Autowired
    private lateinit var storeClient: StoreClient

    @Test
    fun getStores() {
        // given
        // when
        val result = storeClient.getStores()

        // then
        result.size shouldBe 3
    }

    @Test
    fun update() {
        // given
        val store = Store(
            name = "table",
            stock = 100,
        )

        // when
        val result = storeClient.update(1, store)

        // then
        result.id shouldBe 1
        result.name shouldBe "table"
        result.stock shouldBe 100
    }

    @Test
    fun updateWithInvalidIdShouldBeFail() {
        // given
        val store = Store(
            name = "table",
            stock = 100,
        )

        // when
        // then
        shouldThrow<FeignException.InternalServerError> {
            storeClient.update(5, store)
        }
    }
}
