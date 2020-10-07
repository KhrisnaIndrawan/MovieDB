package com.khrisna.core.data.source.remote

import androidx.annotation.Nullable


class ApiResponse<T>() {

    constructor(status: StatusResponse, body: T, message: String?) : this() {
        this.status = status
        this.body = body
        this.message = message
    }

    var status: StatusResponse? = null
    var body: T? = null
    var message: String? = null

    fun <T> success(@Nullable body: T): ApiResponse<T> {
        return ApiResponse(StatusResponse.SUCCESS, body, null)
    }

    fun <T> empty(msg: String, @Nullable body: T): ApiResponse<T> {
        return ApiResponse(StatusResponse.EMPTY, body, msg)
    }

    fun <T> error(msg: String, @Nullable body: T): ApiResponse<T> {
        return ApiResponse(StatusResponse.ERROR, body, msg)
    }
}