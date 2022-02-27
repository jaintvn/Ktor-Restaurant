package com.sample.core.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

/**
 * For JWT Authentication
 * use [generateAccessToken] to create new token
 */
class JWTConfigImpl(secret: String) : JWTConfig{

    private val algorithm = Algorithm.HMAC256(secret)

    override val verifier: JWTVerifier = JWT.require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()

    /**
     * Creates JWT token using [userID]
     */
    override fun generateAccessToken(userID: String): String = JWT.create()
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .withClaim(CLAIM, userID)
        .sign(algorithm)

    companion object {
        private const val ISSUER = "sample_restaurant"
        private const val AUDIENCE = "sample_restaurant"
        const val CLAIM = "userId"
    }
}