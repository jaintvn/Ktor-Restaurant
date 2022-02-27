@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.sample.feature.auth

import io.ktor.locations.*

@Location("/user")
class LoginUser

@Location("/register")
class RegisterUser