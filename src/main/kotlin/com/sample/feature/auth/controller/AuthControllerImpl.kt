package com.sample.feature.auth.controller

import com.sample.core.BaseResponse
import com.sample.feature.auth.AuthRequest
import com.sample.feature.auth.repository.AuthRepository
import com.sample.feature.auth.validate
import com.sample.util.acceptOrThrowException
import io.ktor.application.*

/**
 * For connecting AuthRoutes with AuthRepository. Data validation are happening here.
 */
class AuthControllerImpl(private val repository: AuthRepository) : AuthController {

    /**
     * User login
     */
    override suspend fun login(request: ApplicationCall): BaseResponse<Any> {
        val user = request.acceptOrThrowException<AuthRequest>("User body required!!")
        user.validate()
        return repository.loginUser(user)
    }

    /**
     * Register new user
     */
    override suspend fun registerUser(request: ApplicationCall): BaseResponse<Any> {
        val user = request.acceptOrThrowException<AuthRequest>("User body required!!")
        user.validate()
        return repository.registerUser(user)
    }
}
