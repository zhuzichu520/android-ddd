package com.zhuzichu.application.assembler;


import com.zhuzichu.application.vo.ArticleVo;
import com.zhuzichu.domain.entity.Article;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleAssembler {

    ArticleAssembler INSTANCE = Mappers.getMapper(ArticleAssembler.class);

    ArticleVo toArticleVo(Article article);

}
