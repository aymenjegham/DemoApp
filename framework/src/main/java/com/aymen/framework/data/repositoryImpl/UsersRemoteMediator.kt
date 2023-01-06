package com.aymen.framework.data.repositoryImpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.aymen.framework.dataSource.api.APIClient
import com.aymen.framework.database.Database
import com.aymen.framework.entity.RemoteKeys
import com.aymen.framework.entity.UserEntity
import com.aymen.framework.global.helper.AppObjectMapper
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class UsersRemoteMediator(
    private val api: APIClient,
    private val database: Database,
    private val mapper: AppObjectMapper,
) : RemoteMediator<Int, UserEntity>() {

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)

        return if (System.currentTimeMillis() - (database.remoteKeysDao().getCreationTime()
                ?: 0) < cacheTimeout
        ) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>,
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val apiResponse = api.getUsers(page = page)

            val users = apiResponse.users
            val endOfPaginationReached = users.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().clearRemoteKeys()
                    database.userDao().deleteAll()
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = users.map {
                    RemoteKeys(
                        userId = it.login.uuid,
                        prevKey = prevKey,
                        currentPage = page,
                        nextKey = nextKey)
                }

                database.remoteKeysDao().insertAll(remoteKeys)

                users.map { user ->
                    mapper.map(user, UserEntity::class).copy(
                        firstName = user.name.first,
                        lastName = user.name.last,
                        title = user.name.title,
                        idValue = user.login.uuid,
                        large = user.picture.large,
                        medium = user.picture.medium,
                        thumbnail = user.picture.thumbnail,
                        md5 = user.login.md5,
                        password = user.login.password,
                        salt = user.login.salt,
                        sha1 = user.login.sha1,
                        sha256 = user.login.sha256,
                        username = user.login.username,
                        uuid = user.login.uuid
                    )
                }.run {
                    database.userDao()
                        .insertAll(
                            this.onEachIndexed { _, userEntity ->
                                userEntity.page = page
                            }
                        )
                }

            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, UserEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.idValue?.let { id ->
                database.remoteKeysDao().getRemoteKeyByUserID(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, UserEntity>): RemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { user ->
            database.remoteKeysDao().getRemoteKeyByUserID(user.idValue)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, UserEntity>): RemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { user ->
            database.remoteKeysDao().getRemoteKeyByUserID(user.idValue)
        }
    }
}