package com.zhuzichu.application.assembler;


import com.zhuzichu.application.vo.UserVo;
import com.zhuzichu.domain.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserAssembler {

    UserAssembler INSTANCE = Mappers.getMapper(UserAssembler.class);

    UserVo toUserVo(User user);

}
