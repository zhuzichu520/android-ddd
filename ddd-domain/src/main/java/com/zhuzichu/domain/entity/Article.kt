package com.zhuzichu.domain.entity

import androidx.core.text.HtmlCompat
import com.zhuzichu.shared.tool.removeAllBank

class Article(
    var apkLink: String? = null,
    var audit: Int? = null,
    var author: String? = null,
    var canEdit: Boolean? = null,
    var chapterId: Int? = null,
    var chapterName: String? = null,
    var collect: Boolean? = null,
    var courseId: Int? = null,
    var desc: String? = null,
    var descMd: String? = null,
    var envelopePic: String? = null,
    var fresh: Boolean? = null,
    var host: String? = null,
    var id: Int? = null,
    var link: String? = null,
    var niceDate: String? = null,
    var niceShareDate: String? = null,
    var origin: String? = null,
    var prefix: String? = null,
    var projectLink: String? = null,
    var publishTime: Long? = null,
    var realSuperChapterId: Int? = null,
    var selfVisible: Int? = null,
    var shareDate: Long? = null,
    var shareUser: String? = null,
    var superChapterId: Int? = null,
    var superChapterName: String? = null,
    var tags: List<Any?>? = null,
    var title: String? = null,
    var type: Int? = null,
    var userId: Int? = null,
    var visible: Int? = null,
    var zan: Int? = null
) {

    var top: Boolean? = null

    var displayAuthor: String? = null

    var displayDesc: String? = null

    var chapter: String? = null

    fun handleData(isTop: Boolean? = false) {
        this.top = isTop
        chapter = "${superChapterName}：${chapterName}"
        displayAuthor = if (!author.isNullOrEmpty()) author else shareUser ?: "神秘人"
        displayDesc = HtmlCompat.fromHtml(
            desc ?: "",
            HtmlCompat.FROM_HTML_MODE_COMPACT
        ).toString().removeAllBank()
    }

}