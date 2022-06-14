package com.zhuzichu.domain.repository

import com.zhuzichu.domain.entity.User

interface UserRepository {

    suspend fun login(username: String?, password: String?): User

    fun getUserByLocal(): User
}