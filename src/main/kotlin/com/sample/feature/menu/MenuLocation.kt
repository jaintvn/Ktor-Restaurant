@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.sample.feature.menu

import io.ktor.locations.*

@Location("v1/menu")
class CreateMenu

@Location("v1/menus")
class AllMenu

@Location("v1/menu")
data class SingleMenuItem(val menuId: String)

