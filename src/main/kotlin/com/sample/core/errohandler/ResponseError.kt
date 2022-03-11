package com.sample.core.errohandler

data class ResponseErrors(val errors: Errors) {
    data class Errors(val errors: List<ErrorData?> = listOf())
}

data class ErrorData(val message: String?)