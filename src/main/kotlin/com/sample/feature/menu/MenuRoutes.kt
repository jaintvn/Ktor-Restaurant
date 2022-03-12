@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.sample.feature.menu

import com.sample.feature.menu.controller.MenuController
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.locations.put
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Application.menuRoutes() {

    val controller: MenuController by inject()

    routing {

        //Get All Menu items
        get<AllMenu> {
            call.respond(controller.fetchAllMenuItems(this))
        }

        //Get single Menu ID by Id
        get<SingleMenuItem> { request ->
            call.respond(controller.fetchMenuItemById(request))
        }

        authenticate {
            //Create new Menu item
            post<CreateMenu> {
                call.respond(controller.addMenuItem(this))
            }

            //Delete menu item by ID
            delete<SingleMenuItem> { request ->
                call.respond(controller.deleteMenuItem(request))
            }

            //Update menu item by ID and object passed
            put<SingleMenuItem> {
                call.respond(controller.updateMenuItem(this))
            }
        }
    }
}