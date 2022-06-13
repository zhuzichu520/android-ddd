package com.zhuzichu.infrastructure.converter;

import com.zhuzichu.domain.entity.User;
import com.zhuzichu.infrastructure.dto.LoginDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    User toUser(LoginDto dto);

}
