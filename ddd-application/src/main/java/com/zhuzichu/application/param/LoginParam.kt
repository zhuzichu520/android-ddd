package com.zhuzichu.application.param

import com.zhuzichu.shared.exception.BizException

data class LoginParam(
    val username: String?,
    val password: String?
) : BaseParam {

    override fun check() {
        if (username.isNullOrBlank()) {
            throw BizException("用户名不能为空")
        }
        if (password.isNullOrBlank()) {
            throw BizException("密码不能为空")
        }
    }

}
