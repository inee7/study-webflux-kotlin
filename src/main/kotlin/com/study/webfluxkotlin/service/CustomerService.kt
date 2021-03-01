package com.study.webfluxkotlin.service

import com.study.webfluxkotlin.model.Customer

interface CustomerService {
    fun getCustomer(id: Int): Customer?
    fun searchCustomers(nameFilter: String): List<Customer>
}