package com.sample.feature.order.models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Order(
    @BsonId
    val orderID: String = ObjectId().toString(),
    val orderTotal: Double,
    val items: List<OrderMenuItem>,
    val notes: String? = null,
    val paymentMode: String,
    val createdBy: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val isCompleted: Boolean = false
)