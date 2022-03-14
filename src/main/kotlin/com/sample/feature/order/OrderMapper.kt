package com.sample.feature.order

import com.sample.feature.menu.MenuItem
import com.sample.feature.menu.service.MenuAPiService
import com.sample.feature.order.models.Order
import com.sample.feature.order.models.OrderMenuItem
import com.sample.feature.order.models.OrderRequest
import com.sample.feature.order.models.OrderResponse
import java.util.*


/**
 * To map [OrderRequest] to [Order]
 */
suspend fun mapOrderDbInsert(order: OrderRequest, userId: String, menuService: MenuAPiService): Order {
    val (total, items) = generateData(order.items, menuService)
    return Order(
        orderTotal = total,
        items = mapToOrderMenuItem(items),
        notes = order.notes,
        paymentMode = order.paymentMode,
        createdBy = userId,
        createdAt = Date().toInstant().toString(),
    )
}

/**
 * To map [OrderResponse] to db insertable formatted [OrderResponse]
 */
fun mapOrderResponse(order: Order, listItems: List<OrderMenuItem>): OrderResponse {
    return OrderResponse(
        orderID = order.orderID,
        items = listItems,
        orderTotal = order.orderTotal,
        paymentMode = order.paymentMode
    )
}

/**
 * map [List<MenuItem>] to [List<OrderMenuItem>]
 */
fun mapToOrderMenuItem(listItems: List<MenuItem>): List<OrderMenuItem> {
    return listItems.map { item ->
        OrderMenuItem(
            menuId = item.menuId,
            name = item.name,
            description = item.description,
            imageUrl = item.imageUrl,
            price = item.price
        )
    }
}