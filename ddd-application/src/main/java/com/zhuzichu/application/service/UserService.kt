package com.zhuzichu.application.service

import com.zhuzichu.application.param.LoginParam
import com.zhuzichu.application.vo.UserVo

interface UserService {

    @Throws(Exception::class)
    suspend fun login(loginParam: LoginParam): UserVo

}