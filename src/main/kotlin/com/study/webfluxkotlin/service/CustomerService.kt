package com.study.webfluxkotlin.service

import com.study.webfluxkotlin.model.Customer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CustomerService {
    fun getCustomer(id: Int): Customer?
    fun searchCustomers(nameFilter: String): List<Customer>

    fun getMonoCustomer(id:Int): Mono<Customer>?
    fun searchFluxCustomers(nameFilter: String): Flux<Customer>

    fun createCustomer(customerMono: Mono<Customer>) : Mono<*>

}