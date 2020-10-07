package com.khrisna.core.data.source.vo

import androidx.annotation.Nullable


class Resource<T>() {

    var status: Status? = null
    var data: T? = null
    private var message: String? = null

    constructor(status: Status, data: T, message: String?) : this() {
        this.status = status
        this.data = data
        this.message = message
    }

    fun <T> success(@Nullable data: T): Resource<T> {
        return Resource(Status.SUCCESS, data, null)
    }

    fun <T> error(msg: String, @Nullable data: T): Resource<T> {
        return Resource(Status.ERROR, data, msg)
    }

    fun <T> loading(@Nullable data: T): Resource<T> {
        return Resource(Status.LOADING, data, null)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }

        val resource = other as Resource<*>?

        if (status !== resource!!.status) {
            return false
        }
        return if (if (message != null) message != resource!!.message else resource!!.message != null) {
            false
        } else data?.equals(resource.data) ?: (resource.data == null)
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (message?.hashCode() ?: 0)
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\''.toString() +
                ", data=" + data +
                '}'.toString()
    }
}