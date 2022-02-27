package com.sample.core.auth

import io.ktor.auth.Principal

data class UserIdPrincipalForUser(val userId: String) : Principal
