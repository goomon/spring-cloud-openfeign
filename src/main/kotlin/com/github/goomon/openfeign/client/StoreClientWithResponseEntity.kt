package com.github.goomon.openfeign.client

import com.github.goomon.openfeign.client.configuration.FormFeignEncoderConfiguration
import com.github.goomon.openfeign.domain.model.Store
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
    name = "stores-with-reponse-entity",
    url = "http://localhost:9090",
    configuration = [FormFeignEncoderConfiguration::class],
)
interface StoreClientWithResponseEntity {
    @GetMapping("/stores")
    fun getStores(): ResponseEntity<List<Store>>

    @GetMapping("/stores/server-error")
    fun triggerServerError(): ResponseEntity<List<Store>>

    @GetMapping("/stores/client-error")
    fun triggerClientError(): ResponseEntity<List<Store>>
}