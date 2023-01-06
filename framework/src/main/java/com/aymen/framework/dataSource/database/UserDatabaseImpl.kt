package com.aymen.framework.dataSource.database


import com.aymen.framework.database.UserDao
import com.aymen.framework.entity.UserEntity
import javax.inject.Inject

class UserDatabaseImpl @Inject constructor(
    private val userDao: UserDao,
) : UserDatabase {
    override fun getAll(): List<UserEntity>? = userDao.getAll()

    override fun insertAll(users: List<UserEntity>) = userDao.insertAll(users)

    override fun deleteAll() = userDao.deleteAll()


}