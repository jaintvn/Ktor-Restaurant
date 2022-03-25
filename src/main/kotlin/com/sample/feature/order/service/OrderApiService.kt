package com.sample.feature.order.service

import com.sample.feature.order.models.OrderRequest
import com.sample.feature.order.models.OrderResponse

interface OrderApiService {

    /**
     * To create new order
     * [orderRequest] - item to insert
     * [userId] - Id of user to add in db to track created user
     */
    suspend fun createOrder(orderRequest: OrderRequest, userId: String): OrderResponse?

    /**
     * Get single order item from db
     */
    suspend fun findOrder(orderId: String): OrderResponse?

    /**
     * To fetch all orders from db
     */
    suspend fun fetchAllOrders(): List<OrderResponse>

    /**
     * Set order complete status to TRUE/ FALSE
     * [orderId] : Order to update status
     * [isCompleted] : status to update
     */
    suspend fun setOrderCompleteStatus(orderId: String, isCompleted: Boolean): Boolean
}