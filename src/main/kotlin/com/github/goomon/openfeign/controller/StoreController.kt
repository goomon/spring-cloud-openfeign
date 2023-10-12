package com.github.goomon.openfeign.controller

import com.github.goomon.openfeign.domain.model.Store
import java.lang.IllegalArgumentException
import java.lang.NullPointerException
import java.lang.RuntimeException
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StoreController {
    private val sampleData = listOf(
        Store(
            id = 1,
            name = "monitor",
            stock = 10,
        ),
        Store(
            id = 2,
            name = "mouse",
            stock = 15,
        ),
        Store(
            id = 3,
            name = "keyboard",
            stock = 20,
        )
    )

    @GetMapping("/stores")
    fun getStores(): List<Store> {
        return sampleData
    }

    @PutMapping("/stores/{storeId}")
    fun update(
        @PathVariable storeId: Long,
        @RequestBody store: Store,
    ): Store {
        return sampleData.firstOrNull { it.id == storeId }?.copy(
               name = store.name,
                stock = store.stock,
            ) ?: throw IllegalArgumentException("cannot find store matched with storeId")
    }

    @DeleteMapping("/stores/{storeId}")
    fun delete(@PathVariable storeId: Long) {
        return
    }
}