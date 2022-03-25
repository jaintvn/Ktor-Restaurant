package com.sample.feature.order.controller

import com.sample.core.BaseResponse
import com.sample.feature.order.SingleOrder
import io.ktor.application.*
import io.ktor.util.pipeline.*

interface OrderController {

    /**
     * To create new order
     */
    suspend fun createOrder(request: PipelineContext<Unit, ApplicationCall>): BaseResponse<Any>

    /**
     * Get order by id
     */
    suspend fun findOrderByID(request: SingleOrder): BaseResponse<Any>

    /**
     * Fetch all orders
     */
    suspend fun fetchAllOrders(): BaseResponse<Any>

    /**
     * Set order complete status to TRUE / FALSE
     */
    suspend fun setOrderCompleteStatus(request: PipelineContext<Unit, ApplicationCall>): BaseResponse<Any>
}