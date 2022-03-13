package com.sample.feature.menu.service

import com.mongodb.client.model.Aggregates.project
import com.mongodb.client.model.Aggregates.sort
import com.mongodb.client.model.Projections.fields
import com.sample.core.db.Database
import com.sample.feature.menu.MenuItem
import com.sample.feature.menu.mapMenuDbInsert
import com.sample.feature.menu.mapMenuDbUpdate
import org.bson.conversions.Bson
import org.litote.kmongo.coroutine.aggregate
import org.litote.kmongo.descending
import org.litote.kmongo.exclude

class MenuApiServiceImpl(private val database: Database) :
    MenuAPiService {

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

    /**
     * To fetch single [MenuItem] using ID passed
     */
    override suspend fun fetchMenuItemById(menuId: String): MenuItem? {
        return database.menuCollection.findOneById(menuId)
    }

    /**
     * Add New [MenuItem]
     * [menuItem] - Item to add
     * [userId] - ID of created user
     */
    override suspend fun addMenuItem(menuItem: MenuItem, userId: String): Boolean {
        return database.menuCollection.insertOne(mapMenuDbInsert(menuItem, userId)).wasAcknowledged()
    }

    /**
     * Update existing [MenuItem] in collection
     * [menuItem] - Item to update
     * [menuId] - menuID to update data
     */
    override suspend fun updateMenuItem(menuItem: MenuItem, menuId: String): Boolean {
        return database.menuCollection.updateOneById(
            menuId,
            mapMenuDbUpdate(menuItem, menuId),
            updateOnlyNotNullProperties = true
        ).wasAcknowledged()
    }

    /**
     * To delete [MenuItem] from db using menuID passed.
     * @return status of delete operation
     */
    override suspend fun deleteMenuItem(menuId: String): Boolean {
        return database.menuCollection.deleteOneById(menuId).wasAcknowledged()
    }

}