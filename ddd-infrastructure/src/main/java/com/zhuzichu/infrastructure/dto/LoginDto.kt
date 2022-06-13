package com.zhuzichu.infrastructure.dto

class LoginDto(
    val admin: Boolean? = null,
    val chapterTops: List<Any?>? = null,
    val coinCount: Int? = null,
    val collectIds: List<Int?>? = null,
    val email: String? = null,
    val icon: String? = null,
    val id: Int? = null,
    val nickname: String? = null,
    val password: String? = null,
    val publicName: String? = null,
    val token: String? = null,
    val type: Int? = null,
    val username: String? = null
)