package com.sample.feature.menu.controller

import com.sample.core.BaseResponse
import com.sample.feature.menu.MenuItem
import com.sample.feature.menu.repository.MenuRepository
import com.sample.feature.menu.validate
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
        return menuRepository.addMenuItem(menu, request.getUserId())
    }
}