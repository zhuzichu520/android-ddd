package com.zhuzichu.infrastructure.converter;

import com.zhuzichu.domain.entity.Banner;
import com.zhuzichu.infrastructure.dto.BannerDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BannerConverter {

    BannerConverter INSTANCE = Mappers.getMapper(BannerConverter.class);

    Banner toBanner(BannerDto dto);

}
