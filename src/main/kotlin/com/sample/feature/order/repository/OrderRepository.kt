package com.sample.feature.order.repository

import com.sample.core.BaseResponse
import com.sample.feature.order.models.OrderRequest

interface OrderRepository {

    /**
     * To create new order
     * [order] - Order inserting in db
     * [userId] - user id to track created user
     */
    suspend fun createOrder(order: OrderRequest, userId: String): BaseResponse<Any>

    /**
     * Get single order using order id
     */
    suspend fun findOrderByID(orderId: String): BaseResponse<Any>

    /**
     * Fetch all orders
     */
    suspend fun fetchAllOrders(): BaseResponse<Any>
}