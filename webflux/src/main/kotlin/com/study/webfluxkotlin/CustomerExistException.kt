package com.study.webfluxkotlin

import java.lang.Exception

class CustomerExistException(override val message: String) : Exception(message)