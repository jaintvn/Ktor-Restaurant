package com.sample.core

import io.ktor.http.*

sealed class BaseResponse<T>(val data: T? = null, val statusCode: HttpStatusCode? = null, val message: String? = null) {
    class SuccessResponse<T>(data: T?, statusCode: HttpStatusCode? = null) : BaseResponse<T>(data)
}