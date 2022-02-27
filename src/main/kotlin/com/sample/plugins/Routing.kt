package com.sample.plugins

import com.sample.feature.auth.authRoutes
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.locations.*

/**
 * Install [Locations] and configure all feature routes
 */
fun Application.configureRouting() {
    install(Locations)
    routing {
        authRoutes()
    }
}
