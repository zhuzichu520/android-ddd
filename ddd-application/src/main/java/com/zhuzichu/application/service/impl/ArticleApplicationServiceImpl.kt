package com.zhuzichu.application.service.impl

import com.zhuzichu.application.assembler.ArticleAssembler
import com.zhuzichu.application.assembler.BannerAssembler
import com.zhuzichu.application.service.ArticleApplicationService
import com.zhuzichu.application.vo.ArticleVo
import com.zhuzichu.application.vo.BannerVo
import com.zhuzichu.application.vo.PageVo
import com.zhuzichu.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleApplicationServiceImpl @Inject constructor(
    private val articleRepository: ArticleRepository
) : ArticleApplicationService {

    override suspend fun getTopArticle(): List<ArticleVo> {
        return articleRepository.getTopArticle()?.map {
            it.handleData(true)
            ArticleAssembler.INSTANCE.toArticleVo(it)
        } ?: listOf()
    }

    override suspend fun getArticleList(page: Int): PageVo<Any> {
        val list = mutableListOf<Any>()
        if (page == 0) {
            list.addAll(getTopArticle())
        }
        return PageVo<Any>().assemble(articleRepository.getArticleList(page)) {
            val data = it?.map { item ->
                item.handleData()
                ArticleAssembler.INSTANCE.toArticleVo(item)
            } ?: listOf()
            list.addAll(data)
            list
        }
    }

    override suspend fun getBannerList(): List<BannerVo> {
        return articleRepository.getBanner()?.map {
            BannerAssembler.INSTANCE.toBannerVo(it)
        } ?: listOf()
    }

}