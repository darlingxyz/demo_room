package com.example.demo_room.room

import androidx.room.*

@Dao
interface UserDao {

    @Insert  // 增
    fun insertUser(user: User)

    @Delete  // 删
    fun deleteUser(user: User)

    @Query("select * from user")  // 查
    fun getAllUser(): List<User>

    @Update  // 改
    fun updateUser(user: User)

}