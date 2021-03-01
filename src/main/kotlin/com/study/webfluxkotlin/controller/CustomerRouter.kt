package com.study.webfluxkotlin.controller

import com.study.webfluxkotlin.model.Customer
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.router
import reactor.kotlin.core.publisher.toMono

@Component
class CustomerRouter {
    @Bean
    fun customerRoutes(): RouterFunction<*> = router {
        "/fun".nest {
            "/customer".nest {
                GET("/") {
                    ok().body(Customer(1, "fun web").toMono(), Customer::class.java)
                }
            }
        }
    }
}