package com.aymen.framework.global.helper

import com.aymen.core.domain.user.User


sealed class Navigation {


    data class Back(val ShouldFinish: Boolean) : Navigation()

    data class NavigationDetails(val userItem: User) : Navigation()


}