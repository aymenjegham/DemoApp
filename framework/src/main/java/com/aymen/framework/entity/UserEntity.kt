package com.aymen.framework.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "User", indices = [Index(value = ["id"], unique = true)])
data class UserEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("value")
    val idValue: String,

    @ColumnInfo(name = "phone")
    @Expose
    @SerializedName("phone")
    val phone: String,

    @Expose
    @SerializedName("first")
    val firstName: String,

    @Expose
    @SerializedName("last")
    val lastName: String,

    @Expose
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "email")
    @Expose
    @SerializedName("email")
    val email: String,

    @ColumnInfo(name = "gender")
    @Expose
    @SerializedName("gender")
    val gender: String,

    @ColumnInfo(name = "nat")
    @Expose
    @SerializedName("nat")
    val nat: String,

    @Expose
    @SerializedName("large")
    val large: String,

    @Expose
    @SerializedName("medium")
    val medium: String,

    @Expose
    @SerializedName("thumbnail")
    val thumbnail: String,

    @Expose
    @SerializedName("page")
    var page: Int?,

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

    ) : Serializable