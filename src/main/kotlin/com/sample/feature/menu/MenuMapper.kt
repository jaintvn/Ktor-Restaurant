package com.sample.feature.menu

import java.util.*

/**
 * Map [MenuItem] to DB insertable format
 */
fun mapMenuDbInsert(menuItem: MenuItem, userId: String?): MenuItem {
    return MenuItem(
        name = menuItem.name,
        imageUrl = menuItem.imageUrl,
        description = menuItem.description,
        price = menuItem.price,
        isAvailable = menuItem.isAvailable,
        createdAt = Date().toInstant().toString(),
        createdBy = userId
    )
}

/**
 * Map [MenuItem] to DB updatable format
 */
fun mapMenuDbUpdate(menuItem: MenuItem, menuId: String): MenuItem {
    return MenuItem(
        menuId = menuId,
        name = menuItem.name,
        price = menuItem.price,
        imageUrl = menuItem.imageUrl,
        isAvailable = menuItem.isAvailable,
        description = menuItem.description,
        updatedAt = Date().toInstant().toString()
    )
}