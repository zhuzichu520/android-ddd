package com.zhuzichu.application.assembler;


import com.zhuzichu.application.vo.BannerVo;
import com.zhuzichu.domain.entity.Banner;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BannerAssembler {

    BannerAssembler INSTANCE = Mappers.getMapper(BannerAssembler.class);

    BannerVo toBannerVo(Banner banner);

}
