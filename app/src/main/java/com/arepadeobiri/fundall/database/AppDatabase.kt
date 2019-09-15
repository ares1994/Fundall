package com.arepadeobiri.fundall.database


import android.content.Context

import androidx.room.Database

import androidx.room.Room

import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {


    abstract val userDao: UserDao


    companion object {

        @Volatile

        private var INSTANCE: AppDatabase? = null


        fun getInstance(context: Context): AppDatabase {


            synchronized(this) {


                var instance = INSTANCE

                // If instance is `null` make a new database instance.

                if (instance == null) {

                    instance = Room.databaseBuilder(

                        context.applicationContext,

                        AppDatabase::class.java,

                        "user_table"

                    )


                        .fallbackToDestructiveMigration()

                        .build()



                    INSTANCE = instance

                }

                return instance

            }

        }

    }


}