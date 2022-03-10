package com.sample.core.db

import com.sample.feature.menu.MenuItem
import com.sample.feature.user.User
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

class DataBaseImpl(private val dataBaseName: String) : Database {

    override val dbName: String
        get() = dataBaseName

    override val mongoClient: CoroutineClient
        get() = KMongo.createClient().coroutine

    override val database: CoroutineDatabase
        get() = mongoClient.getDatabase(dbName)

    override val userCollection: CoroutineCollection<User>
        get() = database.getCollection()

    override val menuCollection: CoroutineCollection<MenuItem>
        get() = database.getCollection()
}