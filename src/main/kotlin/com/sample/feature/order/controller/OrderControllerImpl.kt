package com.sample.feature.order.controller

import com.sample.core.BaseResponse
import com.sample.core.errohandler.ExceptionHandler
import com.sample.feature.order.SingleOrder
import com.sample.feature.order.models.OrderRequest
import com.sample.feature.order.repository.OrderRepository
import com.sample.feature.order.validate
import com.sample.util.acceptOrThrowException
import com.sample.util.getUserId
import io.ktor.application.*
import io.ktor.util.pipeline.*

class OrderControllerImpl(private val orderRepository: OrderRepository, private val errorHandler: ExceptionHandler) :
    OrderController {

    override suspend fun createOrder(request: PipelineContext<Unit, ApplicationCall>): BaseResponse<Any> {
        val order = request.call.acceptOrThrowException<OrderRequest>("Order body required")
        order.validate()
        request.getUserId()?.let {
            return orderRepository.createOrder(order, it)
        } ?: throw errorHandler.respondUnAuthorizedException(("User ID  not found!"))
    }

    override suspend fun findOrderByID(request: SingleOrder): BaseResponse<Any> {
        request.orderId?.let {
            return orderRepository.findOrderByID(it)
        } ?: throw errorHandler.respondBadRequestException("Invalid order ID")
    }

    override suspend fun fetchAllOrders(): BaseResponse<Any> {
        return orderRepository.fetchAllOrders()
    }

}