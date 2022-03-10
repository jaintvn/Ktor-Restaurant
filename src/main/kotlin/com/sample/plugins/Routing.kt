package com.sample.plugins

import com.sample.feature.auth.authRoutes
import com.sample.feature.menu.menuRoutes
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.routing.*

/**
 * Install [Locations] and configure all feature routes
 */
fun Application.configureRouting() {
    install(Locations)
    routing {
        authRoutes()
        menuRoutes()
    }
}
