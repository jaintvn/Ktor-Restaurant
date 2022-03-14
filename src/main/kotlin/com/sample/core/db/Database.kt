package com.sample.core.db

import com.sample.feature.menu.MenuItem
import com.sample.feature.order.models.Order
import com.sample.feature.user.User
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase

interface Database {
    val dbName: String
    val mongoClient: CoroutineClient
    val database: CoroutineDatabase
    val userCollection: CoroutineCollection<User>
    val menuCollection: CoroutineCollection<MenuItem>
    val orderCollection: CoroutineCollection<Order>
}