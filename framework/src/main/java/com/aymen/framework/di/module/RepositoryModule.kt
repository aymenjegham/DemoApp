package com.aymen.framework.di.module

import com.aymen.core.data.repository.user.UserRepository
import com.aymen.framework.data.repositoryImpl.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsUserRepository(userRepository: UserRepositoryImpl) : UserRepository

}