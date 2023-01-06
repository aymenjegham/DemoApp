package com.aymen.framework.di.module


import com.aymen.framework.dataSource.database.UserDatabase
import com.aymen.framework.dataSource.database.UserDatabaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindsUserDatabase(userDatabaseImpl: UserDatabaseImpl): UserDatabase


}