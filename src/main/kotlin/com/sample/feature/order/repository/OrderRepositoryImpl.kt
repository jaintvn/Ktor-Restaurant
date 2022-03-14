package com.sample.feature.order.repository

import com.sample.core.BaseResponse
import com.sample.core.errohandler.ExceptionHandler
import com.sample.feature.order.OrderErrors
import com.sample.feature.order.models.OrderRequest
import com.sample.feature.order.models.OrderResponse
import com.sample.feature.order.service.OrderApiService
import io.ktor.http.*

class OrderRepositoryImpl(private val orderService: OrderApiService, private val errorHandler: ExceptionHandler) :
    OrderRepository {

    override suspend fun createOrder(order: OrderRequest, userId: String): BaseResponse<Any> {
        val response: OrderResponse? = orderService.createOrder(order, userId)
        if (response != null) {
            return BaseResponse.SuccessResponse(statusCode = HttpStatusCode.Created, data = response)
        } else {
            throw errorHandler.respondGenericException()
        }
    }

    override suspend fun findOrderByID(orderId: String): BaseResponse<Any> {
        orderService.findOrderByID(orderId)?.let {
            return BaseResponse.SuccessResponse(statusCode = HttpStatusCode.OK, data = it)
        } ?: run { throw errorHandler.respondNotFoundException(OrderErrors.ORDER_NOT_EXIST) }
    }

    override suspend fun fetchAllOrders(): BaseResponse<Any> {
        val orders = orderService.fetchAllOrders()
        return BaseResponse.SuccessResponse(statusCode = HttpStatusCode.OK, data = orders)
    }
}