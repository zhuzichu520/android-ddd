package com.zhuzichu.domain.entity

import com.tencent.mmkv.MMKV
import com.zhuzichu.shared.tool.StringPreference
import com.zhuzichu.shared.tool.json2Object
import com.zhuzichu.shared.tool.object2Json

class User(
    var nickname: String? = null,
    var password: String? = null,
    var publicName: String? = null,
    var token: String? = null,
    var type: Int? = null,
    var username: String? = null
) {


    companion object {
        private const val PREFS_NAME = "User"
    }

    private val prefs: Lazy<MMKV> = lazy {
        MMKV.mmkvWithID(PREFS_NAME)
    }

    private var sourceData by StringPreference(prefs, null)

    /**
     * 保存本地登录信息
     */
    fun saveLocal() {
        sourceData = object2Json(this)
    }

    /**
     * 清除本地登录信息
     */
    fun clearLocal() {
        sourceData = null
    }

    fun fillLocal() {
        json2Object(sourceData, User::class.java)
    }

}