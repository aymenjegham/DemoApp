package com.aymen.core.domain.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Login(

    @Expose
    @SerializedName("md5")
    val md5: String,

    @Expose
    @SerializedName("password")
    val password: String,

    @Expose
    @SerializedName("salt")
    val salt: String,

    @Expose
    @SerializedName("sha1")
    val sha1: String,

    @Expose
    @SerializedName("sha256")
    val sha256: String,

    @Expose
    @SerializedName("username")
    val username: String,

    @Expose
    @SerializedName("uuid")
    val uuid: String
)