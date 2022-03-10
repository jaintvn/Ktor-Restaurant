package com.sample.util

import com.sample.core.auth.UserIdPrincipalForUser
import com.sample.core.errohandler.MissingRequestBodyException
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.request.*
import io.ktor.util.pipeline.*

/**
 * To get body content
 */
suspend inline fun <reified T : Any> PipelineContext<*, ApplicationCall>.getBodyContent(): T {
    return call.receive()
}

/**
 * It returns T if request has body. Else throw MissingRequestBodyException with error message
 */
suspend inline fun <reified T : Any> ApplicationCall.acceptOrThrowException(errorMessage: String): T {
    return this.receiveOrNull() ?: throw MissingRequestBodyException(errorMessage)
}

/**
 * To extract user id from request call
 */
fun PipelineContext<*, ApplicationCall>.getUserId(): String? {
    val principal = this.call.authentication.principal<UserIdPrincipalForUser>()
    return principal?.userId
}