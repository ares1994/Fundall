package com.arepadeobiri.fundall.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = false)
    val databaseId: Int = 1,

    @ColumnInfo
    val email: String,

    @ColumnInfo
    val firstName: String,

    @ColumnInfo
    val lastName: String,

    @ColumnInfo
    val avatarUrl: String




)