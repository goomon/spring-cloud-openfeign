package com.github.goomon.openfeign

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class OpenfeignApplication

fun main(args: Array<String>) {
    runApplication<OpenfeignApplication>(*args)
}
