package com.zhuzichu.application.module

import com.zhuzichu.application.assembler.UserAssembler
import com.zhuzichu.application.service.UserService
import com.zhuzichu.application.service.impl.UserServiceImpl
import com.zhuzichu.domain.repository.UserRepository
import com.zhuzichu.infrastructure.converter.UserConverter
import com.zhuzichu.infrastructure.repository.impl.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UserModule {

    @Provides
    fun provideUserRepository(
        userConverter: UserConverter
    ): UserRepository {
        return UserRepositoryImpl(userConverter)
    }

    @Provides
    fun provideUserService(
        userRepository: UserRepository,
        userAssembler: UserAssembler
    ): UserService {
        return UserServiceImpl(userRepository, userAssembler)
    }


}