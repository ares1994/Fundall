package com.arepadeobiri.fundall.database


import androidx.lifecycle.LiveData
import androidx.room.Dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy

import androidx.room.Query
import com.arepadeobiri.fundall.database.User


@Dao

interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Query("SELECT * FROM user_table")
    fun getAll(): LiveData<List<User>>

    @Query("DELETE FROM user_table")
    fun clear()


}