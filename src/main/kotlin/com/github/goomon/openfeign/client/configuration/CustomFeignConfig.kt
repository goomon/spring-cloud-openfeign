package com.github.goomon.openfeign.client.configuration

import feign.Retryer
import org.springframework.context.annotation.Configuration

@Configuration
class CustomFeignConfig {
//    @Bean
    fun retryer(): Retryer {
        return Retryer.Default()
    }
}
