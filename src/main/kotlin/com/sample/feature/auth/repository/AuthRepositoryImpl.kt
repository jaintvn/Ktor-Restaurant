package com.sample.feature.auth.repository

import com.sample.core.BaseResponse
import com.sample.core.auth.JWTConfig
import com.sample.core.errohandler.ExceptionHandler
import com.sample.feature.auth.AuthErrors
import com.sample.feature.auth.AuthRequest
import com.sample.feature.user.User
import com.sample.feature.user.service.UserApiService
import com.sample.util.generateHash
import com.sample.util.isHashMatches
import io.ktor.http.*
import java.util.*

/**
 * Repository class for auth related functionalities.
 * [loginUser] : For user login
 * [registerUser] : For new user registration
 */
class AuthRepositoryImpl(
    private val userApiService: UserApiService,
    private val jwtConfig: JWTConfig,
    private val exceptionHandler: ExceptionHandler
) : AuthRepository {

    /**
     * Validates user by checking password hash stored in db with generated hash using request password.
     */
    override suspend fun loginUser(authRequest: AuthRequest): BaseResponse<Any> {
        return if (isUserExist(authRequest.username)) {
            val user = userApiService.findUserByUsername(authRequest.username)
            if (user != null) {
                when (user.passwordHash?.let { isHashMatches(authRequest.password, it) }) {
                    true -> BaseResponse.SuccessResponse(
                        data = jwtConfig.generateAccessToken(user.userId),
                        statusCode = HttpStatusCode.OK
                    )
                    else -> throw exceptionHandler.respondUnAuthorizedException(AuthErrors.EITHER_USERNAME_PASSWORD_INCORRECT)
                }
            } else throw exceptionHandler.respondUnAuthorizedException(AuthErrors.NOT_AUTHORIZED)
        } else {
            throw exceptionHandler.respondUnAuthorizedException(AuthErrors.USER_DONT_EXIST_MESSAGE)
        }
    }

    /**
     * Register new user with generated password hash using request password field.
     * For success insertion, it responds with new JWT token generated
     */
    override suspend fun registerUser(authRequest: AuthRequest): BaseResponse<Any> {
        return if (!isUserExist(authRequest.username)) {
            val hashPassword = generateHash(authRequest.password)
            val user = User(
                userName = authRequest.username,
                passwordHash = hashPassword,
                createdAT = Date().toInstant().toString()
            )
            when (userApiService.addUser(user)) {
                true -> {
                    BaseResponse.SuccessResponse(
                        data = jwtConfig.generateAccessToken(user.userId),
                        statusCode = HttpStatusCode.Created
                    )
                }
                else -> throw exceptionHandler.respondGenericException(AuthErrors.SOMETHING_WENT_WRONG)
            }
        } else {
            throw exceptionHandler.respondAlreadyExistException(AuthErrors.USER_ALREADY_EXIST_MESSAGE)
        }
    }

    private suspend fun isUserExist(userName: String) = userApiService.findUserByUsername(userName) != null
}