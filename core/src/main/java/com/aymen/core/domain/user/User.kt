package com.aymen.core.domain.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(

    val userId: String,

    @Expose
    @SerializedName("phone")
    val phone: String,

    @Expose
    @SerializedName("name")
    val name: Name,

    @Expose
    @SerializedName("email")
    val email: String,

    @Expose
    @SerializedName("gender")
    val gender: String,

    @Expose
    @SerializedName("nat")
    val nat: String,

    @Expose
    @SerializedName("picture")
    val picture: Picture,

    @Expose
    @SerializedName("login")
    val login: Login,

    var page :Int?

    ) : Serializable{

    val userFullName: String
        get() = "${name.first} ${name.last}"
    }