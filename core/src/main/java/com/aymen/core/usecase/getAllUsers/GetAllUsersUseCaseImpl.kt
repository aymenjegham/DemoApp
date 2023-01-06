package com.aymen.core.usecase.getAllUsers

import com.aymen.core.data.repository.user.UserRepository
import com.aymen.core.domain.user.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllUsersUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    GetAllUsersUseCase {

    override fun invoke(): Flow<com.aymen.core.domain.Result<List<User>>?> = userRepository.getAllUsers()

}