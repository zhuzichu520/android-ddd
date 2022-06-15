package com.zhuzichu.domain.repository

import com.zhuzichu.domain.entity.Article
import com.zhuzichu.domain.entity.Page


interface ArticleRepository {

    suspend fun getTopArticle(): List<Article>?


    suspend fun getArticleList(): Page<Article>?

}