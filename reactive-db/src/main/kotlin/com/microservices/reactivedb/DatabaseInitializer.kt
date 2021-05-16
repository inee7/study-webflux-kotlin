package com.microservices.reactivedb

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DatabaseInitializer {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var mongoOperations: ReactiveMongoOperations

    companion object {
        val initialCustomers = listOf(
            Customer(1, "kotlin"),
            Customer(2, "Spring"),
            Customer(3, "Microservice", Customer.Telephone("+33", "123213123"))

        )
    }

    @PostConstruct
    fun initData() {
        customerRepository.saveAll(initialCustomers).subscribe {
            println("Default customers created")
        }
    }
}