package com.aymen.testapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.aymen.core.data.repository.user.UserRepository
import com.aymen.core.usecase.getAllUsers.GetAllUsersUseCaseImpl
import com.aymen.testapp.repository.user.FakeUserListRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test


class HomeViewModelTest{

    private lateinit var viewModel: HomeViewModel

    private lateinit var getAllUsersUseCase: GetAllUsersUseCaseImpl
    private lateinit var userRepository: UserRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `fetching_users_returns_successfully`(){

        userRepository = FakeUserListRepository(false)
        getAllUsersUseCase = GetAllUsersUseCaseImpl(userRepository)

        viewModel = HomeViewModel(
            ApplicationProvider.getApplicationContext(),
            getAllUsersUseCase
        )

        viewModel.fetchUsers()

        val value = viewModel.resultUserList.getOrAwaitValue()

        assertThat(value?.status).isEqualTo(com.aymen.core.domain.Result.Status.SUCCESS)


    }

    @Test
    fun `fetching_users_returns_Error`(){

        userRepository = FakeUserListRepository(true)
        getAllUsersUseCase = GetAllUsersUseCaseImpl(userRepository)

        viewModel = HomeViewModel(
            ApplicationProvider.getApplicationContext(),
            getAllUsersUseCase
        )

        viewModel.fetchUsers()

        val value = viewModel.resultUserList.getOrAwaitValue()

        assertThat(value?.status).isEqualTo(com.aymen.core.domain.Result.Status.ERROR)


    }

}