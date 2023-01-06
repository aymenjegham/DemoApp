package com.aymen.core.data.repository.user

import androidx.paging.PagingData
import com.aymen.core.domain.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<PagingData<User>>

}