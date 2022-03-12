package com.sample.feature.menu.repository

import com.sample.core.BaseResponse
import com.sample.core.errohandler.ExceptionHandler
import com.sample.feature.auth.AuthErrors
import com.sample.feature.menu.MenuErrors
import com.sample.feature.menu.MenuItem
import com.sample.feature.menu.service.MenuAPiService
import io.ktor.http.*

class MenuRepositoryImpl(private val menuService: MenuAPiService, private val exceptionHandler: ExceptionHandler) :
    MenuRepository {

    /**
     * Fetch all menu items added
     */
    override suspend fun fetchAllMenuItems(): BaseResponse<Any> {
        val items = menuService.fetchAllMenuItems()
        return BaseResponse.SuccessResponse(statusCode = HttpStatusCode.OK, data = items)
    }

    override suspend fun fetchMenuItemById(menuId: String?): BaseResponse<Any> {
        val menuItem = menuService.fetchMenuItemById(menuId)
        if (menuItem != null) {
            return BaseResponse.SuccessResponse(statusCode = HttpStatusCode.OK, data = menuItem)
        } else {
            throw exceptionHandler.respondNotFoundException(MenuErrors.MENU_NOT_EXIST)
        }
    }

    /**
     * Add New [MenuItem]
     * [menuItem] - Item to add
     * [userId] - ID of created user
     */
    override suspend fun addMenuItem(menuItem: MenuItem, userId: String?): BaseResponse<Any> {
        if (userId != null) {
            if (menuService.addMenuItem(menuItem, userId)) {
                return BaseResponse.SuccessResponse(statusCode = HttpStatusCode.Created, data = true)
            } else {
                throw exceptionHandler.respondGenericException()
            }
        } else {
            throw exceptionHandler.respondUnAuthorizedException(AuthErrors.NOT_AUTHORIZED)
        }
    }

    /**
     * Update existing [MenuItem] in collection. It update only if data exist in collection.
     * [menuItem] - Item to update
     * [menuId] - menuID to update data
     */
    override suspend fun updateMenuItem(menuItem: MenuItem, menuId: String): BaseResponse<Any> {
        if (isMenuItemExist(menuId)) {
            if (menuService.updateMenuItem(menuItem, menuId)) {
                return BaseResponse.SuccessResponse(statusCode = HttpStatusCode.Created, data = true)
            } else {
                throw exceptionHandler.respondGenericException()
            }
        } else {
            throw exceptionHandler.respondNotFoundException(MenuErrors.MENU_NOT_EXIST)
        }
    }

    /**
     * Delete single [MenuItem] using menuId
     */
    override suspend fun deleteMenuItem(menuId: String): BaseResponse<Any> {
        if (isMenuItemExist(menuId)) {
            if (menuService.deleteMenuItem(menuId)) {
                return BaseResponse.SuccessResponse(statusCode = HttpStatusCode.Created, data = true)
            } else {
                throw exceptionHandler.respondGenericException()
            }
        } else {
            throw exceptionHandler.respondNotFoundException(MenuErrors.MENU_NOT_EXIST)
        }
    }

    /**
     * To check [MenuItem] existing in db using menuID
     */
    private suspend fun isMenuItemExist(menuId: String): Boolean {
        return menuService.fetchMenuItemById(menuId) != null
    }

}