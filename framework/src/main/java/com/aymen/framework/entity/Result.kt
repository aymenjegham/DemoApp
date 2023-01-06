package com.aymen.framework.entity

import com.aymen.core.domain.user.Info
import com.aymen.core.domain.user.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @Expose
    @SerializedName("results")
    val users: List<User>,

    @Expose
    @SerializedName("info")
    val info: Info


)