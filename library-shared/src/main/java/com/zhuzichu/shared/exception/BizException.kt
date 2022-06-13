package com.zhuzichu.shared.exception

class BizException(
    override val message: String?,
    val code: Int? = -1,
) : RuntimeException()