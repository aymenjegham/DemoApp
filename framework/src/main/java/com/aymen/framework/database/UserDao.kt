package com.aymen.framework.database

import androidx.paging.PagingSource
import androidx.room.*
import com.aymen.framework.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

    @Query("SELECT * FROM User order By page")
    fun getAll(): PagingSource<Int, UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserEntity>)

    @Query("DELETE FROM User")
    suspend fun deleteAll()


}