package com.sample.plugins

import com.sample.core.coreModules
import com.sample.feature.auth.authModules
import com.sample.feature.user.userModules
import io.ktor.application.*
import org.koin.ktor.ext.koin

/**
 * Configure koin modules
 */
fun Application.configureKoin() {
    koin{
        modules(coreModules)
        modules(authModules)
        modules(userModules)
    }
}