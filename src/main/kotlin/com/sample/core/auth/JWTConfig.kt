package com.sample.core.auth

import com.auth0.jwt.JWTVerifier

interface JWTConfig {
    val verifier : JWTVerifier
    fun generateAccessToken(userID : String) : String
}