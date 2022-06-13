package com.zhuzichu.infrastructure.converter

import com.zhuzichu.infrastructure.dto.LoginDto
import com.zhuzichu.domain.entity.User
import com.zhuzichu.shared.tool.object2Object
import javax.inject.Inject

class UserConverter @Inject constructor() {

    fun toUser(loginDto: LoginDto?): User {
        val user = object2Object(loginDto, User::class.java)
        return user!!
    }

}