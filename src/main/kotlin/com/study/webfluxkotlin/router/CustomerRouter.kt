package com.study.webfluxkotlin.router

import com.study.webfluxkotlin.handler.CustomerHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class CustomerRouter(val customerHandler: CustomerHandler) {
    @Bean
    fun customerRoutes() = router {
        "/fun".nest {
            "/customer".nest {
                GET("/{id}", customerHandler::get)
                POST("/", customerHandler::create)
            }
            "/customers".nest {
                GET("", customerHandler::search)
            }
        }
    }
}