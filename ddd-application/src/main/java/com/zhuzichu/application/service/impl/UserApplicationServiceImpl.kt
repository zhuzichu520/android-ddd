package com.zhuzichu.application.service.impl

import com.zhuzichu.application.assembler.UserAssembler
import com.zhuzichu.application.param.LoginParam
import com.zhuzichu.application.service.UserApplicationService
import com.zhuzichu.application.vo.UserVo
import com.zhuzichu.domain.repository.UserRepository
import javax.inject.Inject

class UserApplicationServiceImpl @Inject constructor(
    private val userRepository: UserRepository
) : UserApplicationService {

    override suspend fun login(loginParam: LoginParam): UserVo {
        loginParam.check()
        val user = userRepository.login(loginParam.username, loginParam.password)
        return UserAssembler.INSTANCE.toUserVo(user)
    }

    override fun getUserByLocal(): UserVo {
        return UserAssembler.INSTANCE.toUserVo(userRepository.getUserByLocal())
    }

}