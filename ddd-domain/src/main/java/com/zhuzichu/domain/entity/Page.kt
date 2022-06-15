package com.zhuzichu.domain.entity

class Page<T>(
    var curPage: Int? = null,
    var datas: List<T>? = null,
    var offset: Int? = null,
    var over: Boolean? = null,
    var pageCount: Int? = null,
    var size: Int? = null,
    var total: Int? = null
)