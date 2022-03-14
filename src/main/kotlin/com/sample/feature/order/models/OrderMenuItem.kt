package com.sample.feature.order.models

data class OrderMenuItem(
    val menuId: String,
    val name: String,
    val description: String? = null,
    val imageUrl: String? = null,
    val price: Double? = null
)