package com.sample.feature.order

import io.ktor.locations.*

@Location("v1/order")
class Order

@Location("v1/order")
data class SingleOrder(val orderId: String?)

@Location("v1/orders")
class Orders

@Location("v1/order/status")
class OrderStatus