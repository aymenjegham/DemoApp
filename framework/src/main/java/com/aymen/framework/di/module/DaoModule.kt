package com.aymen.framework.di.module

import com.aymen.framework.database.Database
import com.aymen.framework.database.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DaoModule {


    @Provides
    @Singleton
    fun provideProductDao(database: Database): UserDao = database.userDao()

}