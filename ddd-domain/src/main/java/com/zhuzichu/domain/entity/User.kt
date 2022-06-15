package com.zhuzichu.domain.entity

import com.tencent.mmkv.MMKV
import com.zhuzichu.shared.tool.StringPreference
import com.zhuzichu.shared.tool.json2Object
import com.zhuzichu.shared.tool.object2Json

class User(
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
) {

    private val prefs: Lazy<MMKV> = lazy {
        MMKV.mmkvWithID(PREFS_NAME)
    }

    private var sourceDataPref by StringPreference(prefs, null)

    /**
     * 保存本地登录信息
     */
    fun saveSourceData(json: String?) {
        sourceDataPref = json
    }

    fun getSourceData(): String? {
        return sourceDataPref
    }

    /**
     * 移除本地登录信息
     */
    fun removeSourceData() {
        sourceDataPref = null
    }

    companion object {
        private const val PREFS_NAME = "User"
    }


}