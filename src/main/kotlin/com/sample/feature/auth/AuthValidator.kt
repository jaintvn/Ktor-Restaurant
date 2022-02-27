package com.sample.feature.auth

import org.valiktor.ConstraintViolationException
import org.valiktor.functions.isNotNull
import kotlin.jvm.Throws

@Throws(ConstraintViolationException::class)
fun AuthRequest.validate(){
    org.valiktor.validate(this){
        validate(AuthRequest::username).isNotNull()
        validate(AuthRequest::password).isNotNull()
    }
}