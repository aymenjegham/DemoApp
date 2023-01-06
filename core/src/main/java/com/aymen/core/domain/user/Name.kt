package com.aymen.core.domain.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Name(

    @Expose
    @SerializedName("first")
    val first: String,

    @Expose
    @SerializedName("last")
    val last: String,

    @Expose
    @SerializedName("title")
    val title: String
) : Serializable