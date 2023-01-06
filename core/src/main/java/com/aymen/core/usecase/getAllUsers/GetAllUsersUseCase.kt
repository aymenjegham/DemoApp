package com.aymen.core.usecase.getAllUsers


import com.aymen.core.domain.user.User
import kotlinx.coroutines.flow.Flow

interface GetAllUsersUseCase {

    operator fun invoke(): Flow<com.aymen.core.domain.Result<List<User>>?>
}