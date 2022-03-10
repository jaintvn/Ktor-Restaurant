package com.sample.feature.menu.repository

import com.sample.core.BaseResponse
import com.sample.feature.menu.MenuItem

interface MenuRepository {

    suspend fun fetchAllMenuItems(): BaseResponse<Any>

    suspend fun fetchMenuItemById(menuId: String): MenuItem?

    suspend fun addMenuItem(menuItem: MenuItem, userId: String?): BaseResponse<Any>

    suspend fun updateMenuItem(menuItem: MenuItem, userId: String): Boolean

    suspend fun deleteMenuItem(menuId: String): Boolean
}