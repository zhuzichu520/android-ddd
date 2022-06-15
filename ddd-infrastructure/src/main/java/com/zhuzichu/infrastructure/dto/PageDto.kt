package com.zhuzichu.infrastructure.dto

import com.zhuzichu.domain.entity.Page

class PageDto<T>(
    var curPage: Int? = null,
    var datas: List<T>? = null,
    var offset: Int? = null,
    var over: Boolean? = null,
    var pageCount: Int? = null,
    var size: Int? = null,
    var total: Int? = null
) {

    fun <E> convert(func: (List<T>?) -> List<E>?): Page<E> {
        val page = Page<E>()
        page.curPage = curPage
        page.offset = offset
        page.over = over
        page.pageCount = pageCount
        page.size = size
        page.total = total
        page.datas = func.invoke(datas)
        return page
    }
}