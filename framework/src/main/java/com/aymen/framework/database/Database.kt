package com.aymen.framework.database


import androidx.room.RoomDatabase
import com.aymen.framework.entity.RemoteKeys
import com.aymen.framework.entity.UserEntity
import com.aymen.testapp.global.constants.DATABASE_FILE_NAME
import androidx.room.Database as RoomDatabse


const val DATABASE_NAME = DATABASE_FILE_NAME
const val DATABASE_VERSION = 1

@RoomDatabse(
    entities = [
        UserEntity::class,
        RemoteKeys::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)


abstract class Database : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}