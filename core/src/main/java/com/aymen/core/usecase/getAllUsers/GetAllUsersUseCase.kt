package com.aymen.core.usecase.getAllUsers


import androidx.paging.PagingData
import com.aymen.core.domain.user.User
import kotlinx.coroutines.flow.Flow

interface GetAllUsersUseCase {

    operator fun invoke(): Flow<PagingData<User>>
}