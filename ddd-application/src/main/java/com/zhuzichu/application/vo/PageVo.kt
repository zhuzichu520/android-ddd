package com.zhuzichu.application.vo

import com.zhuzichu.domain.entity.Article
import com.zhuzichu.domain.entity.Page

class PageVo<V>(
    var curPage: Int? = null,
    var datas: List<V>? = null,
    var offset: Int? = null,
    var over: Boolean? = null,
    var pageCount: Int? = null,
    var size: Int? = null,
    var total: Int? = null
) {

    fun <T> assemble(page: Page<T>?, func: (List<T>?) -> List<V>?): PageVo<V> {
        curPage = page?.curPage
        datas = func.invoke(page?.datas)
        offset = page?.offset
        over = page?.over
        pageCount = page?.pageCount
        size = page?.size
        total = page?.total
        return this
    }

}