package com.aymen.framework.di.module

import com.aymen.core.usecase.getAllUsers.GetAllUsersUseCase
import com.aymen.core.usecase.getAllUsers.GetAllUsersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindResetPassword(getAllUsersUseCaseImpl: GetAllUsersUseCaseImpl): GetAllUsersUseCase

}