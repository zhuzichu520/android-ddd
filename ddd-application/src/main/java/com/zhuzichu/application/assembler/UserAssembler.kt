package com.zhuzichu.application.assembler

import com.zhuzichu.application.vo.UserVo
import com.zhuzichu.domain.entity.User
import javax.inject.Inject


class UserAssembler @Inject constructor() {

    fun toUserVo(user: User): UserVo {
        return UserVo(user.nickname)
    }


}