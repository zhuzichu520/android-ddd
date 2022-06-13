package com.zhuzichu.infrastructure.dto

import com.google.gson.annotations.SerializedName
import com.zhuzichu.shared.exception.BizException

data class Response<T>(
    @SerializedName("data")
    val `data`: T?,
    @SerializedName("errorCode")
    val errorCode: Int?,
    @SerializedName("errorMsg")
    val errorMsg: String?
) {
    fun check(): T? {
        if (errorCode != 0)
            throw BizException(errorMsg,errorCode)
        return data
    }
}