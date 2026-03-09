package com.restaurante

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestauranteApiApplication

fun main(args: Array<String>) {
    runApplication<RestauranteApiApplication>(*args)
}
