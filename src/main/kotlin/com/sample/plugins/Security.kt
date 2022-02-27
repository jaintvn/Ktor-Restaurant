package com.sample.plugins

import com.sample.core.auth.JWTConfig
import com.sample.core.auth.JWTConfigImpl
import com.sample.core.auth.UserIdPrincipalForUser
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import org.koin.ktor.ext.inject

/**
 * JWT token configuration
 */
fun Application.configureSecurity() {

    val jwtConfig: JWTConfig by inject()

    install(Authentication) {
        jwt {
            verifier(jwtConfig.verifier)
            validate {
                val claim = it.payload.getClaim(JWTConfigImpl.CLAIM).asString()
                if (claim != null) {
                    UserIdPrincipalForUser(claim)
                } else null
            }
        }
    }
}