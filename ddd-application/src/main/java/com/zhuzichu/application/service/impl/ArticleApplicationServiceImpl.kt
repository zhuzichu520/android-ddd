package com.zhuzichu.application.service.impl

import com.zhuzichu.application.service.ArticleApplicationService
import com.zhuzichu.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleApplicationServiceImpl @Inject constructor(
    private val articleRepository: ArticleRepository
) : ArticleApplicationService {


}