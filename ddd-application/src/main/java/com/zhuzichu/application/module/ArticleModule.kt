package com.zhuzichu.application.module

import com.zhuzichu.application.service.ArticleApplicationService
import com.zhuzichu.application.service.impl.ArticleApplicationServiceImpl
import com.zhuzichu.domain.repository.ArticleRepository
import com.zhuzichu.infrastructure.repository.impl.ArticleRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ArticleModule {

    @Provides
    fun provideArticleRepository(): ArticleRepository {
        return ArticleRepositoryImpl()
    }

    @Provides
    fun provideArticleService(
        articleRepository: ArticleRepository
    ): ArticleApplicationService {
        return ArticleApplicationServiceImpl(articleRepository)
    }


}