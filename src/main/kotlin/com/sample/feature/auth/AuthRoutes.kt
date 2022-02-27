@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.sample.feature.auth

import com.sample.feature.auth.controller.AuthController
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

/**
 * Routes for Auth module
 */
fun Application.authRoutes() {

    val controller: AuthController by inject()

    routing {
        post<LoginUser> {
            val response = controller.login(call)
            call.respond(response)
        }

        post<RegisterUser> {
            val response = controller.registerUser(call)
            call.respond(response)
        }
    }
}
