package com.zhuzichu.application.service.impl

import com.zhuzichu.application.assembler.ArticleAssembler
import com.zhuzichu.application.service.ArticleApplicationService
import com.zhuzichu.application.vo.ArticleVo
import com.zhuzichu.application.vo.PageVo
import com.zhuzichu.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleApplicationServiceImpl @Inject constructor(
    private val articleRepository: ArticleRepository
) : ArticleApplicationService {

    override suspend fun getTopArticle(): List<ArticleVo> {
        return articleRepository.getTopArticle()?.map {
            ArticleAssembler.INSTANCE.toArticleVo(it)
        } ?: listOf()
    }

    override suspend fun getArticleList(): PageVo<ArticleVo> {
        return PageVo<ArticleVo>().assemble(articleRepository.getArticleList()) {
            it?.map { item ->
                ArticleAssembler.INSTANCE.toArticleVo(item)
            }
        }
    }


}