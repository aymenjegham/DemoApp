package com.aymen.core.domain.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Info(
    @Expose
    @SerializedName("page")
    val page: Int,

    @Expose
    @SerializedName("results")
    val results: Int,

    @Expose
    @SerializedName("seed")
    val seed: String,

    @Expose
    @SerializedName("version")
    val version: String
)