package com.sample.core.errohandler

import java.lang.Exception

interface ExceptionHandler {

    fun respondGenericException(message: String = "Something Went Wrong") : Exception

    fun respondNotFoundException(message: String) : Exception

    fun respondBadRequestException(message:String) : Exception

    fun respondUnAuthorizedException(message: String) : Exception

    fun respondAlreadyExistException(message: String) : Exception
}