package com.zhuzichu.domain.entity

import com.google.gson.Gson
import com.tencent.mmkv.MMKV
import com.zhuzichu.shared.tool.StringPreference
import com.zhuzichu.shared.tool.json2Object
import com.zhuzichu.shared.tool.object2Json

data class User(
    val nickname: String? = null,
    val password: String? = null,
    val publicName: String? = null,
    val token: String? = null,
    val type: Int? = null,
    val username: String? = null
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
        json2Object(sourceData,User::class.java)
    }

}