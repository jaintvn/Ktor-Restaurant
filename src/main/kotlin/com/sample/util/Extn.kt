package com.sample.util

import com.sample.core.errohandler.MissingRequestBodyException
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.util.pipeline.*

suspend inline fun <reified T : Any> PipelineContext<*, ApplicationCall>.getBodyContent() : T {
    return call.receive()
}

suspend inline fun <reified T : Any> ApplicationCall.acceptOrThrowException(errorMessage:String): T {
    return this.receiveOrNull()?: throw MissingRequestBodyException(errorMessage)
}