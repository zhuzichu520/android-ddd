package com.zhuzichu.infrastructure.repository.impl

import com.zhuzichu.domain.entity.Article
import com.zhuzichu.domain.entity.Page
import com.zhuzichu.domain.repository.ArticleRepository
import com.zhuzichu.infrastructure.converter.ArticleConverter
import com.zhuzichu.infrastructure.dto.ArticleDto
import com.zhuzichu.infrastructure.dto.PageDto
import com.zhuzichu.infrastructure.dto.Response
import rxhttp.toClass
import rxhttp.xxx.RxHttp
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
) : ArticleRepository {

    override suspend fun getTopArticle(): List<Article>? {
        val response = RxHttp.get("/article/top/json")
            .toClass<Response<List<ArticleDto>>>().await()
        return response.check()?.map {
            ArticleConverter.INSTANCE.toArticle(it)
        }
    }

    override suspend fun getArticleList(): Page<Article>? {
        val response = RxHttp.get("/article/list/1/json")
            .toClass<Response<PageDto<ArticleDto>>>().await()
        return response.check()?.convert {
            it?.map { item ->
                ArticleConverter.INSTANCE.toArticle(item)
            }
        }
    }


}