package com.aymen.framework.usecase.getAllUsers

import com.aymen.core.data.repository.user.UserRepository
import com.aymen.core.usecase.getAllUsers.GetAllUsersUseCase
import com.aymen.core.usecase.getAllUsers.GetAllUsersUseCaseImpl
import com.aymen.framework.repository.user.FakeUserListRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetAllUsersUseCaseImplTest {

    private lateinit var getAllUsersUseCase: GetAllUsersUseCase
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp(){
        userRepository = FakeUserListRepository()
        getAllUsersUseCase = GetAllUsersUseCaseImpl(userRepository)
    }

    @Test
    fun `Get users List, correct users list return` (): Unit = runBlocking{
        val users = getAllUsersUseCase().first()
        assertEquals(users?.data?.get(0)?.name, FakeUserListRepository.fakeListOfUsersItems[0].name)
    }

    @Test
    fun `Get users List, incorrect users list return` (): Unit = runBlocking{
        val users = getAllUsersUseCase().first()
        assertNotEquals(users?.data?.get(1)?.email, FakeUserListRepository.fakeListOfUsersItems[0].email)
    }

}