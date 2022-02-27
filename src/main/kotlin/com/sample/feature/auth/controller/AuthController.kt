package com.sample.feature.auth.controller

import com.sample.core.BaseResponse
import io.ktor.application.*

interface AuthController {
    suspend fun login(request: ApplicationCall) : BaseResponse<Any>
    suspend fun registerUser(request: ApplicationCall) : BaseResponse<Any>
}