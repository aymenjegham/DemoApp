package com.aymen.framework.data.repositoryImpl

import androidx.paging.*
import com.aymen.core.data.repository.user.UserRepository
import com.aymen.core.domain.user.Login
import com.aymen.core.domain.user.Name
import com.aymen.core.domain.user.Picture
import com.aymen.core.domain.user.User
import com.aymen.framework.dataSource.api.APIClient
import com.aymen.framework.dataSource.database.UserDatabase
import com.aymen.framework.database.Database
import com.aymen.framework.global.helper.AppObjectMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val api: APIClient,
    private val userDatabase: UserDatabase,
    private val mapper: AppObjectMapper,
    private val database: Database,
) : UserRepository {

    private val pageSize = 30

    @OptIn(
        ExperimentalPagingApi::class,
        ExperimentalCoroutinesApi::class
    )
    override fun getUsers(): Flow<PagingData<User>> =
        Pager(
            config = PagingConfig(
                pageSize = pageSize,
                prefetchDistance = 20,
                initialLoadSize = pageSize,
            ),
            pagingSourceFactory = {
                userDatabase.getAll()
            },
            remoteMediator = UsersRemoteMediator(
                api,
                database,
                mapper
            )
        ).flow.mapLatest {
            it.map { userEntity ->
                mapper.map(userEntity, User::class).copy(
                    name = Name(
                        first = userEntity.firstName,
                        last = userEntity.lastName,
                        title = userEntity.title),
                    userId = userEntity.idValue,
                    picture = Picture(large = userEntity.large,
                        medium = userEntity.medium,
                        thumbnail = userEntity.thumbnail),
                    login = Login(
                        md5 = userEntity.md5,
                        uuid = userEntity.uuid,
                        password = userEntity.password,
                        salt = userEntity.salt,
                        sha1 = userEntity.sha1,
                        sha256 = userEntity.sha256,
                        username = userEntity.username
                    )
                )
            }
        }

}