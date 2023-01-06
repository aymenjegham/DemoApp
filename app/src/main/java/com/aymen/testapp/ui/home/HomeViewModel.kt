package com.aymen.testapp.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aymen.core.domain.user.User
import com.aymen.core.usecase.getAllUsers.GetAllUsersUseCase
import com.aymen.framework.global.helper.Navigation

import com.aymen.testapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    app: Application,
    private val getAllUsersUseCase: GetAllUsersUseCase
) : BaseViewModel(app) {

    private val _resultUserList = MutableLiveData<com.aymen.core.domain.Result<List<User>>>()
    val resultUserList = _resultUserList


    init {
        fetchUsers()
    }

    fun fetchUsers() {

        viewModelScope.launch {
            getAllUsersUseCase().collect {
                _resultUserList.value = it
            }
        }
    }

    fun navigate(): (Int) -> Unit = {
        _resultUserList.value?.data?.get(it)?.let { userItem ->
            navigate(Navigation.NavigationDetails(userItem))
        }
    }
}