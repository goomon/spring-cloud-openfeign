package com.github.goomon.openfeign.client

import com.github.goomon.openfeign.util.measureMs
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ConcurrencyTest {
    @Autowired
    private lateinit var storeClient: StoreClient

    @Test
    fun syncTest() {
        val timeElapsed = measureMs {
            (1..10).forEach { _ ->
                storeClient.getStores()
                Thread.sleep(1000)
            }
        }
        println("timeElapsed: $timeElapsed ms")
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Test
    fun asyncTest() {
        val timeElapsed = measureMs {
            runBlocking(newFixedThreadPoolContext(32, "asyncTest")) {
                (1..10).map { _ ->
                    async {
                        storeClient.getStores()
                    }
                }.awaitAll()
            }
        }
        println("timeElapsed: $timeElapsed ms")
    }
}
