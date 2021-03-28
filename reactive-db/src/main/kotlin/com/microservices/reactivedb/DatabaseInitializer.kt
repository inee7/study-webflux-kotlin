package com.microservices.reactivedb

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class DatabaseInitializer {

    @Autowired
    lateinit var mongoOperations: ReactiveMongoOperations

    @PostConstruct
    fun initData(){
        mongoOperations.collectionExists("customers").subscribe{
            if (!it) {
                mongoOperations.createCollection("customers").subscribe {
                    println("customers collections created")
                }
            }else {
                println("exist")
            }
        }

    }
}