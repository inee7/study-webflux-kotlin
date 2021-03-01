package com.study.webfluxkotlin.service

import com.study.webfluxkotlin.model.Customer
import com.study.webfluxkotlin.model.Customer.Telephone
import org.springframework.stereotype.Service
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

}