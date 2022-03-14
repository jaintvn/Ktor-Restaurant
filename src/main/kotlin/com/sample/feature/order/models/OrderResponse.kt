package com.sample.feature.order.models

data class OrderResponse(
    val orderID: String,
    val orderTotal: Double,
    val items: List<OrderMenuItem>,
    val notes: String? = null,
    val paymentMode: String,
    val createdAt: String? = null,
)