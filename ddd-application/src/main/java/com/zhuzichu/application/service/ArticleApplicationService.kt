package com.zhuzichu.application.service

import com.zhuzichu.application.vo.ArticleVo
import com.zhuzichu.application.vo.BannerVo
import com.zhuzichu.application.vo.PageVo

interface ArticleApplicationService {

    suspend fun getTopArticle(): List<ArticleVo>

    suspend fun getArticleList(page: Int): PageVo<ArticleVo>

    suspend fun getBannerList(): List<BannerVo>

}