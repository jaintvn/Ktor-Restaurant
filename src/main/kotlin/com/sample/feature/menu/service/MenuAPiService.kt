package com.sample.feature.menu.service

import com.sample.feature.menu.MenuItem

interface MenuAPiService {

    suspend fun fetchAllMenuItems(): List<MenuItem>

    suspend fun fetchMenuItemById(menuId: String): MenuItem?

    suspend fun addMenuItem(menuItem: MenuItem, userId: String): Boolean

    suspend fun updateMenuItem(menuItem: MenuItem, menuId: String): Boolean

    suspend fun deleteMenuItem(menuId: String): Boolean
}