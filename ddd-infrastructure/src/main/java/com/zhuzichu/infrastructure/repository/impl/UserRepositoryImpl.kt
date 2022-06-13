package com.zhuzichu.infrastructure.repository.impl

import com.zhuzichu.infrastructure.dto.LoginDto
import com.zhuzichu.infrastructure.dto.Response
import com.zhuzichu.domain.entity.User
import com.zhuzichu.domain.repository.UserRepository
import com.zhuzichu.infrastructure.converter.UserConverter
import rxhttp.toClass
import rxhttp.xxx.RxHttp
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
) : UserRepository {

    override suspend fun login(
        username: String?,
        password: String?
    ): User {
        val response = RxHttp.postJson("/user/login")
            .addQuery("username", username)
            .addQuery("password", password)
            .toClass<Response<LoginDto>>().await()
        return UserConverter.INSTANCE.toUser(response.check())
    }

}