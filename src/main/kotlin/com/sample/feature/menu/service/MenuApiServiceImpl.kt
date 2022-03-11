package com.sample.feature.menu.service

import com.mongodb.client.model.Aggregates.project
import com.mongodb.client.model.Aggregates.sort
import com.mongodb.client.model.Projections.fields
import com.sample.core.db.Database
import com.sample.feature.menu.MenuItem
import org.bson.conversions.Bson
import org.litote.kmongo.coroutine.aggregate
import org.litote.kmongo.descending
import org.litote.kmongo.exclude
import java.util.*

class MenuApiServiceImpl(private val database: Database) : MenuAPiService {

    /**
     * Fetch all menu items from menuCollection. Set order as last created [MenuItem] comes first
     */
    override suspend fun fetchAllMenuItems(): List<MenuItem> {
        val fieldsToExclude: Bson = fields(exclude(MenuItem::updatedAt, MenuItem::createdBy))

        return database.menuCollection.aggregate<MenuItem>(
            project(fieldsToExclude),
            sort(descending(MenuItem::createdAt))
        ).toList()
    }

    override suspend fun fetchMenuItemById(menuId: String?): MenuItem? {
        return menuId?.let { database.menuCollection.findOneById(it) }
    }

    /**
     * Add New [MenuItem]
     * [menuItem] - Item to add
     * [userId] - ID of created user
     */
    override suspend fun addMenuItem(menuItem: MenuItem, userId: String?): Boolean {
        val menuToInsert = MenuItem(
            name = menuItem.name,
            imageUrl = menuItem.imageUrl,
            description = menuItem.description,
            createdAt = Date().toInstant().toString(),
            createdBy = userId
        )
        return database.menuCollection.insertOne(menuToInsert).wasAcknowledged()
    }

    override suspend fun updateMenuItem(menuItem: MenuItem, userId: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMenuItem(menuId: String): Boolean {
        TODO("Not yet implemented")
    }

}