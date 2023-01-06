package com.aymen.framework.dataSource.database


import com.aymen.framework.entity.UserEntity


interface UserDatabase {

    fun getAll(): List<UserEntity>?

    fun insertAll(users: List<UserEntity>)

    fun deleteAll()

}