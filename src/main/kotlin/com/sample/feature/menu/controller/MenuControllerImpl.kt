package com.sample.feature.menu.controller

import com.sample.core.BaseResponse
import com.sample.core.errohandler.AuthenticationException
import com.sample.core.errohandler.MissingRequestBodyException
import com.sample.feature.menu.MenuItem
import com.sample.feature.menu.SingleMenuItem
import com.sample.feature.menu.repository.MenuRepository
import com.sample.feature.menu.validate
import com.sample.feature.menu.validateUpdate
import com.sample.util.acceptOrThrowException
import com.sample.util.getUserId
import io.ktor.application.*
import io.ktor.util.pipeline.*

class MenuControllerImpl(private val menuRepository: MenuRepository) : MenuController {

    /**
     * To fetch all menu items added
     */
    override suspend fun fetchAllMenuItems(request: PipelineContext<Unit, ApplicationCall>): BaseResponse<Any> {
        return menuRepository.fetchAllMenuItems()
    }

    /**
     * Add new [MenuItem]
     * It requires [MenuItem] body to process
     */
    override suspend fun addMenuItem(request: PipelineContext<Unit, ApplicationCall>): BaseResponse<Any> {
        val menu = request.call.acceptOrThrowException<MenuItem>("Menu Item body required")
        menu.validate()
        request.getUserId()?.let {
            return menuRepository.addMenuItem(menu, it)
        } ?: run { throw AuthenticationException("User ID  not found!") }
    }

    /**
     * Fetch Single [MenuItem] using ID
     */
    override suspend fun fetchMenuItemById(request: SingleMenuItem?): BaseResponse<Any> {
        if (request != null && (request.menuId?.isBlank() == false)) {
            return menuRepository.fetchMenuItemById(request.menuId)
        } else throw MissingRequestBodyException("Invalid menu id")
    }

    /**
     * Delete single [MenuItem] from database.
     * Returns status of operation
     */
    override suspend fun deleteMenuItem(request: SingleMenuItem?): BaseResponse<Any> {
        if (request != null && (request.menuId?.isBlank() == false)) {
            return menuRepository.deleteMenuItem(request.menuId)
        } else throw MissingRequestBodyException("Invalid menu id")
    }

    /**
     * Update single [MenuItem] from collection
     * Return status of updation
     */
    override suspend fun updateMenuItem(request: PipelineContext<Unit, ApplicationCall>): BaseResponse<Any> {
        val menu = request.call.acceptOrThrowException<MenuItem>("Menu Item body required")
        menu.validateUpdate()
        val menuId = request.call.parameters["menuId"]
        if (!menuId.isNullOrBlank()) {
            return menuRepository.updateMenuItem(menu, menuId)
        } else throw MissingRequestBodyException("Invalid menu id")
    }
}