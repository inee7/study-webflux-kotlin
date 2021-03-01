package com.study.webfluxkotlin.controller

import com.study.webfluxkotlin.model.Customer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController {
    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int) = Customer(id, "customer $id")
}