package com.sample.feature.user.service

import com.sample.core.db.Database
import com.sample.feature.user.User
import org.bson.conversions.Bson
import org.litote.kmongo.eq
import org.litote.kmongo.exclude
import org.litote.kmongo.fields

/**
 * API layer having all user related operations. Other features can communicate it via [UserApiService]
 */
class UserApiServiceImpl(private val database: Database) : UserApiService {

    /**
     * Get [User] by using [userId]
     * returns null if empty
     */
    override suspend fun findUserById(userId: String): User? {
        val fields: Bson = fields(exclude(User::passwordHash))
        return userId.let {
            database.userCollection.find(User::userId eq userId)
                .projection(fields)
                .first()
        }
    }

    /**
     * Get [User] by using [userName]
     * returns null if empty
     */
    override suspend fun findUserByUsername(userName: String): User? {
        return database.userCollection.findOne(User::userName eq userName)
    }

    /**
     * Add new User to user collection
     * returns Boolean for success/failure
     */
    override suspend fun addUser(user: User): Boolean {
        return database.userCollection.insertOne(user).wasAcknowledged()
    }
}