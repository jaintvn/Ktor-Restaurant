package com.sample.feature.menu.repository

import com.sample.core.BaseResponse
import com.sample.feature.menu.MenuItem

interface MenuRepository {

    suspend fun fetchAllMenuItems(): BaseResponse<Any>

    suspend fun fetchMenuItemById(menuId: String): BaseResponse<Any>

    suspend fun addMenuItem(menuItem: MenuItem, userId: String): BaseResponse<Any>

    suspend fun updateMenuItem(menuItem: MenuItem, menuId: String): BaseResponse<Any>

    suspend fun deleteMenuItem(menuId: String): BaseResponse<Any>
}