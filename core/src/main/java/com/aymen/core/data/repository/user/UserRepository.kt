package com.aymen.core.data.repository.user

import com.aymen.core.domain.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getAllUsers(): Flow<com.aymen.core.domain.Result<List<User>>?>

}