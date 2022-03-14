@file:OptIn(KtorExperimentalLocationsAPI::class)

package com.sample.feature.order

import com.sample.feature.order.controller.OrderController
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Application.orderRoutes() {

    val orderController: OrderController by inject()

    routing {

        authenticate {

            //To get single order
            get<SingleOrder> { request ->
                call.respond(orderController.findOrderByID(request))
            }

            //Get all orders
            get<Orders> {
                call.respond(orderController.fetchAllOrders())
            }

            //Create new order
            post<Order> {
                call.respond(orderController.createOrder(this))
            }
        }
    }
}