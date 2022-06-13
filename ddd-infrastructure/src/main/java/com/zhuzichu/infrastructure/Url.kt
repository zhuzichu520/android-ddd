package com.zhuzichu.infrastructure

import rxhttp.wrapper.annotation.DefaultDomain

class Url {

    companion object {
        @JvmField
        @DefaultDomain //设置为默认域名
        var baseUrl = "https://www.wanandroid.com"
    }

}