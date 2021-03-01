package com.study.webfluxkotlin.handler

import com.study.webfluxkotlin.model.Customer
import com.study.webfluxkotlin.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.EntityResponse.fromObject
//import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import java.net.URI

@Component
class CustomerHandler(val customerService: CustomerService) {
    fun get(serverRequest: ServerRequest) =
        ok().body(customerService.getMonoCustomer(serverRequest.pathVariable("id").toInt()))
//            .flatMap { ok().body(fromObject(it)) } //에러남
            .switchIfEmpty(status(HttpStatus.NOT_FOUND).build())

    fun search(serverRequest: ServerRequest) =
        ok().body(
            customerService.searchFluxCustomers(serverRequest.queryParam("nameFilter").orElse("")),
            Customer::class.java
        )

    fun create(serverRequest: ServerRequest) =
        customerService.createCustomer(serverRequest.bodyToMono()).flatMap {
            created(URI.create("/fun/customer/${it.id}")).build()
        }.onErrorResume(Exception::class.java) {
            badRequest().body(fromObject("error").build())
        }
}