package com.humlib

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class HumLibBackendApplication

fun main(args: Array<String>) {
    runApplication<HumLibBackendApplication>(*args)
}
