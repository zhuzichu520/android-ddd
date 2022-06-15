package com.zhuzichu.infrastructure.converter;

import com.zhuzichu.domain.entity.Article;
import com.zhuzichu.infrastructure.dto.ArticleDao;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleConverter {

    ArticleConverter INSTANCE = Mappers.getMapper(ArticleConverter.class);

    Article toArticle(ArticleDao dto);

}
