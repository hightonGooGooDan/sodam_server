package com.damdam.damdambackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@EnableFeignClients
@SpringBootApplication
class DamdamBackendApplication

fun main(args: Array<String>) {
    runApplication<DamdamBackendApplication>(*args)
}
