package com.zhuzichu.application.service

import com.zhuzichu.application.param.LoginParam
import com.zhuzichu.application.vo.UserVo
import com.zhuzichu.domain.entity.User

interface UserApplicationService {

    @Throws(Exception::class)
    suspend fun login(loginParam: LoginParam): UserVo

    fun getUserByLocal(): UserVo

}