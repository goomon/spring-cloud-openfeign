package com.github.goomon.openfeign.client

import com.github.goomon.openfeign.client.configuration.FeignConfiguration
import com.github.goomon.openfeign.domain.model.Store
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping

@FeignClient(
    name = "stores",
    url = "http://localhost:9090",
    configuration = [FeignConfiguration::class]
)
interface StoreClient {
    @GetMapping("/stores")
    fun getStores(): List<Store>

    @GetMapping("/stores/read-timeout")
    fun readTimeout()

    @PutMapping(value = ["/stores/{storeId}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@PathVariable storeId: Long, store: Store): Store

    @DeleteMapping("/stores/{storeId:\\d+}")
    fun delete(@PathVariable storeId: Long)
}
