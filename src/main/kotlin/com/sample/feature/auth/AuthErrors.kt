package com.sample.feature.auth

/**
 * All Auth errors defined here
 */
class AuthErrors {
    companion object{
        const val USER_ALREADY_EXIST_MESSAGE = "User already exists, Please login"
        const val EITHER_USERNAME_PASSWORD_INCORRECT = "Either username or password is incorrect"
        const val NOT_AUTHORIZED = "Not authorised"
        const val USER_DONT_EXIST_MESSAGE = "User doesn't exists, Please register"
        const val SOMETHING_WENT_WRONG = "Something went wrong. Please try again"
    }
}
