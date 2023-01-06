package com.aymen.core.domain.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Id(

    @Expose
    @SerializedName("value")
    val idValue: String?

) : Serializable