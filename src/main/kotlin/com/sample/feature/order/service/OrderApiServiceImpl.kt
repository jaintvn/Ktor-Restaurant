package com.sample.feature.order.service

import com.mongodb.client.model.Aggregates.sort
import com.sample.core.db.Database
import com.sample.feature.menu.service.MenuAPiService
import com.sample.feature.order.mapOrderDbInsert
import com.sample.feature.order.mapOrderResponse
import com.sample.feature.order.models.Order
import com.sample.feature.order.models.OrderRequest
import com.sample.feature.order.models.OrderResponse
import org.litote.kmongo.coroutine.aggregate
import org.litote.kmongo.descending

class OrderApiServiceImpl(private val database: Database, private val menuService: MenuAPiService) : OrderApiService {

    override suspend fun createOrder(orderRequest: OrderRequest, userId: String): OrderResponse? {
        val order = mapOrderDbInsert(orderRequest, userId, menuService)
        return if (database.orderCollection.insertOne(order).wasAcknowledged()) {
            return mapOrderResponse(order, order.items)
        } else null
    }

    override suspend fun findOrderByID(orderId: String): OrderResponse? {
        val order = database.orderCollection.findOneById(orderId)
        order?.let {
            return mapOrderResponse(order, order.items)
        } ?: return null
    }

    override suspend fun fetchAllOrders(): List<OrderResponse> {
        val orders = database.orderCollection.aggregate<Order>(
            sort(descending(Order::createdAt))
        ).toList()
        return orders.map {
            mapOrderResponse(it, it.items)
        }
    }

}