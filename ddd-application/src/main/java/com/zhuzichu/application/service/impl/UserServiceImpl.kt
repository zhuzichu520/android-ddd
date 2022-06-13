package com.zhuzichu.application.service.impl

import com.zhuzichu.application.assembler.UserAssembler
import com.zhuzichu.application.param.LoginParam
import com.zhuzichu.application.service.UserService
import com.zhuzichu.domain.repository.UserRepository
import com.zhuzichu.application.vo.UserVo
import javax.inject.Inject

class UserServiceImpl @Inject constructor(
    private val userRepository: UserRepository
) : UserService {

    override suspend fun login(loginParam: LoginParam): UserVo {
        loginParam.check()
        val user = userRepository.login(loginParam.username, loginParam.password)
        return UserAssembler.INSTANCE.toUserVo(user)
    }

}