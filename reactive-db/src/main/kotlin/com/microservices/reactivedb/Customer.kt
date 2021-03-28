package com.microservices.reactivedb

import org.springframework.data.mongodb.core.mapping.Document

@Document(collation = "customers")
data class Customer(var id: Int = 0, var name: String = "", var telephone: Telephone? = null) {
    data class Telephone(var countryCode: String = "", var telephoneNumber: String = "")
}