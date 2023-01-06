package com.aymen.testapp.ui.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aymen.core.domain.user.User
import com.aymen.testapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    app: Application,
) : BaseViewModel(app) {

    private val _userItem = MutableLiveData<User>()
    val userItem: LiveData<User>
        get() = _userItem

    fun setUserItem(userItem: User) {
        _userItem.postValue(userItem)
    }


}