package com.sample.feature.auth.repository

import com.sample.core.BaseResponse
import com.sample.feature.auth.AuthRequest

interface AuthRepository {

    suspend fun loginUser(authRequest: AuthRequest): BaseResponse<Any>

    suspend fun registerUser(authRequest: AuthRequest): BaseResponse<Any>
}