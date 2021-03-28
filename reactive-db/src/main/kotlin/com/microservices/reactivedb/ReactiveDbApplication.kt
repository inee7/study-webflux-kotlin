package com.microservices.reactivedb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveDbApplication

fun main(args: Array<String>) {
    runApplication<ReactiveDbApplication>(*args)
}
