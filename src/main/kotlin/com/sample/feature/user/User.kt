package com.sample.feature.user

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    val userName: String? = null,
    val passwordHash: String? = null,
    @BsonId
    val userId: String = ObjectId().toString(),
    val createdAT: String? = null
) {
    fun toResponse(): User {
        return User(userName, null, userId)
    }
}