package com.study.webfluxkotlin.service

import com.study.webfluxkotlin.model.Customer
import com.study.webfluxkotlin.model.Customer.Telephone
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap

@Service
class CustomerServiceImpl : CustomerService {

    companion object {
        val mockCustomers = arrayOf(
            Customer(1, "kotlin"),
            Customer(2, "Spring"),
            Customer(3, "Microservice", Telephone("+44", "1234"))
        )
    }

    private val customers = ConcurrentHashMap(mockCustomers.associateBy { it.id })

    override fun getCustomer(id: Int) = customers[id]

    override fun searchCustomers(nameFilter: String) =
        customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int, Customer>::value).toList()

    override fun getMonoCustomer(id: Int) = customers[id]?.toMono() ?: Mono.empty()

    override fun searchFluxCustomers(nameFilter: String) =
        customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int, Customer>::value).toFlux()

    override fun createCustomer(customerMono: Mono<Customer>) =
        customerMono.map {
            customers[it.id] = it
            it
        }

}