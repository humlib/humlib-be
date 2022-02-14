package com.humlib

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HumLibBackendApplication

fun main(args: Array<String>) {
	runApplication<HumLibBackendApplication>(*args)
}
