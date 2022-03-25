package com.sample.feature.order.models

data class OrderStatusRequest(val orderId: String, val status: Boolean)