package com.sample.feature.user.service

import com.sample.feature.user.User

interface UserApiService {

    suspend fun findUserById(userId: String): User?

    suspend fun findUserByUsername(userName: String): User?

    suspend fun addUser(user:User) : Boolean
}