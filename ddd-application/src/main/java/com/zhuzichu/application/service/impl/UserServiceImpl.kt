package com.zhuzichu.application.service.impl

import com.zhuzichu.application.assembler.UserAssembler
import com.zhuzichu.application.param.LoginParam
import com.zhuzichu.application.service.UserService
import com.zhuzichu.domain.repository.UserRepository
import com.zhuzichu.application.vo.UserVo
import com.zhuzichu.domain.entity.User
import javax.inject.Inject

class UserServiceImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val userAssembler: UserAssembler
) : UserService {

    override suspend fun login(loginParam: LoginParam): UserVo {
        loginParam.check()
        val user = userRepository.login(loginParam.username, loginParam.password)
        user.save()
        return userAssembler.toUserVo(user)
    }

    override fun getLocalUser(): UserVo {
        User.Companion
    }

}