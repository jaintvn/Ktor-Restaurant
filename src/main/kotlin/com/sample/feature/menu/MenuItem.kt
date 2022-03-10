package com.sample.feature.menu

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class MenuItem(
    @BsonId val menuId: String = ObjectId().toString(),
    val name: String,
    val description: String? = null,
    val imageUrl: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val createdBy: String? = null
)