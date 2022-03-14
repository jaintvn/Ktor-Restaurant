package com.sample.feature.order

import com.sample.feature.menu.MenuItem
import com.sample.feature.menu.service.MenuAPiService
import com.sample.feature.order.models.ItemWithQuality

const val TAX_PERCENTAGE = .25

/**
 * To get list of [MenuItem] from db based on their Ids also calculate order total
 * @return [Pair<Int,List<MenuItem>>] - order total, list of MenuItems
 */
suspend inline fun generateData(
    items: List<ItemWithQuality>,
    menuService: MenuAPiService
): Pair<Double, List<MenuItem>> {
    val map = items.associate { it.itemID to it.quantity }
    val listIDs = items.map { '"' + it.itemID + '"' }

    val menuItems = menuService.populateBasedOnIds(listIDs)
    return Pair(getTotal(map, menuItems), menuItems)
}

/**
 * Calculate total based on item quantity, price and tax
 */
fun getTotal(map: Map<String, Int>, items: List<MenuItem>): Double {
    var sum = 0.0
    if (items.isNotEmpty()) {
        items.forEach { item ->
            sum += map[item.menuId]?.times((item.price ?: 0.0)) ?: 0.0
        }
    }
    return sum + (sum * (TAX_PERCENTAGE / 100))
}