package com.sample.feature.order

import com.sample.feature.order.models.OrderRequest
import com.sample.feature.order.models.OrderStatusRequest
import org.valiktor.functions.isIn
import org.valiktor.functions.isNotEmpty
import org.valiktor.functions.isNotNull

/**
 * To validate Create order request
 */
fun OrderRequest.validate() {
    org.valiktor.validate(this) {
        validate(OrderRequest::items).isNotNull()
        validate(OrderRequest::items).isNotEmpty()
        validate(OrderRequest::paymentMode).isNotNull()
        validate(OrderRequest::paymentMode).isIn("card", "cash")
    }
}

/**
 * To validate update order status request
 */
fun OrderStatusRequest.validate() {
    org.valiktor.validate(this) {
        validate(OrderStatusRequest::orderId).isNotNull()
        validate(OrderStatusRequest::orderId).isNotEmpty()
        validate(OrderStatusRequest::status).isNotNull()
    }
}