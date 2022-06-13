package com.zhuzichu.infrastructure.dto

class LoginDto(
    var admin: Boolean? = null,
    var chapterTops: List<Any?>? = null,
    var coinCount: Int? = null,
    var collectIds: List<Int?>? = null,
    var email: String? = null,
    var icon: String? = null,
    var id: Int? = null,
    var nickname: String? = null,
    var password: String? = null,
    var publicName: String? = null,
    var token: String? = null,
    var type: Int? = null,
    var username: String? = null
)