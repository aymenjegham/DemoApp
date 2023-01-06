package com.aymen.framework.data.repositoryImpl

import android.content.Context
import com.aymen.core.data.repository.user.UserRepository
import com.aymen.core.domain.user.Id
import com.aymen.core.domain.user.Name
import com.aymen.core.domain.user.Picture
import com.aymen.core.domain.user.User
import com.aymen.framework.dataSource.api.APIClient
import com.aymen.framework.dataSource.database.UserDatabase
import com.aymen.framework.entity.UserEntity
import com.aymen.framework.global.extension.isNetworkAvailable
import com.aymen.framework.global.helper.AppObjectMapper
import com.aymen.framework.global.utils.ErrorUtils
import com.aymen.framework.global.utils.uuid
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: APIClient,
    private val userDatabase: UserDatabase,
    private val retrofit: Retrofit,
    private val mapper: AppObjectMapper,
    @ApplicationContext private val context: Context,
) : UserRepository {

    override fun getAllUsers(): Flow<com.aymen.core.domain.Result<List<User>>?> {
        return flow {
            emit(fetchCachedUsers())
            emit(com.aymen.core.domain.Result.loading())
            val result = getResponse(
                request = {
                    api.getAllUsers()
                },
                defaultErrorMessage = "Error fetching users list"
            )

            //Cache to database if response is successful
            if (result.status == com.aymen.core.domain.Result.Status.SUCCESS) {
                result.data?.let { it ->
                    val mappedResult = it.map { user ->
                        mapper.map(user, UserEntity::class).copy(
                            firstName = user.name.first,
                            lastName = user.name.last,
                            title = user.name.title,
                            idValue = user.id.idValue?: uuid(),
                            large = user.picture.large,
                            medium = user.picture.medium,
                            thumbnail = user.picture.thumbnail
                        )
                    }
                    userDatabase.insertAll(mappedResult)
                }
            }

            emit(fetchCachedUsers())

        }.flowOn(Dispatchers.IO)
    }

    private fun fetchCachedUsers(): com.aymen.core.domain.Result<List<User>>? =
        userDatabase.getAll()?.let { userEntityList ->
            val mappedResult = userEntityList.map { userEntity ->
                mapper.map(userEntity, User::class).copy(
                    name = Name(
                        userEntity.firstName,
                        userEntity.lastName,
                        userEntity.title,
                    ),
                    picture = Picture(
                        userEntity.large,
                        userEntity.medium,
                        userEntity.thumbnail
                    ),
                    id = Id(
                        userEntity.idValue
                    )
                )
            }
            com.aymen.core.domain.Result.success(mappedResult)
        }

    private suspend fun getResponse(
        request: suspend () -> Response<com.aymen.framework.entity.Result>,
        defaultErrorMessage: String,
    ): com.aymen.core.domain.Result<List<User>> {
        return try {
            val result = request()

            if (result.isSuccessful) {
                val mappedResult = result.body()?.users
                return com.aymen.core.domain.Result.success(mappedResult)
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                com.aymen.core.domain.Result.error(errorResponse?.status_message
                    ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            if (context.isNetworkAvailable()) {
                com.aymen.core.domain.Result.error("Unknown Error ", null)
            } else {
                com.aymen.core.domain.Result.error("No network available", null)
            }

        }
    }

}