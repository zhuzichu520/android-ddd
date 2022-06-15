package com.zhuzichu.infrastructure.repository.impl

import com.zhuzichu.infrastructure.dto.LoginDto
import com.zhuzichu.infrastructure.dto.Response
import com.zhuzichu.domain.entity.User
import com.zhuzichu.domain.repository.UserRepository
import com.zhuzichu.infrastructure.converter.UserConverter
import com.zhuzichu.shared.tool.json2Object
import com.zhuzichu.shared.tool.object2Json
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
        val loginDto = response.check()
        val user = UserConverter.INSTANCE.toUser(loginDto)
        user.saveSourceData(object2Json(loginDto))
        return user
    }

    override fun getUserByLocal(): User {
        var user = User()
        val loginDto = json2Object(user.getSourceData(), LoginDto::class.java)
        user = UserConverter.INSTANCE.toUser(loginDto)
        return user
    }

}