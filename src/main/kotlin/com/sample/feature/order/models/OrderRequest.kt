package com.sample.feature.order.models

data class OrderRequest(
    val items: List<ItemWithQuality>,
    val notes: String? = null,
    val paymentMode: String,
    val createdBy: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
)