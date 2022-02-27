package com.sample.plugins

import com.sample.core.errohandler.ResponseErrors
import com.sample.core.errohandler.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.features.BadRequestException
import io.ktor.features.NotFoundException
import io.ktor.http.*
import io.ktor.response.*
import org.valiktor.ConstraintViolationException

/**
 * Handler for all exceptions thrown in app define here.
 * It used [ResponseErrors] to format error
 */
fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<AuthenticationException> { cause ->
            call.respond(HttpStatusCode.Unauthorized, ResponseErrors.Errors(listOf(cause.message)))
        }
        exception<AuthorizationException> { cause ->
            call.respond(HttpStatusCode.Forbidden, ResponseErrors.Errors(listOf(cause.message)))
        }
        exception<BadRequestException> { cause ->
            call.respond(HttpStatusCode.BadRequest, ResponseErrors.Errors(listOf(cause.message)))
        }
        exception<NotFoundException> { cause ->
            call.respond(HttpStatusCode.NotFound, ResponseErrors.Errors(listOf(cause.message)))
        }
        exception<ConflictException> { cause ->
            call.respond(HttpStatusCode.Conflict, ResponseErrors.Errors(listOf(cause.message)))
        }
        exception<SomethingWentWrongException> { cause ->
            call.respond(HttpStatusCode.ExpectationFailed, ResponseErrors.Errors(listOf(cause.message)))
        }
        exception<MissingRequestBodyException> { cause ->
            call.respond(HttpStatusCode.BadRequest, ResponseErrors.Errors(listOf(cause.message)))
        }
        exception<ConstraintViolationException> { cause ->
            val violations =
                cause.constraintViolations.map { violation -> "${violation.property}:${violation}" }
            call.respond(HttpStatusCode.BadRequest, ResponseErrors.Errors(violations))
        }
    }
}