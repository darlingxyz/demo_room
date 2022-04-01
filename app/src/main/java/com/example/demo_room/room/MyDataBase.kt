package com.example.demo_room.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [User::class], exportSchema = false)
abstract class MyDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var instance: MyDataBase? = null
        @Synchronized
        fun getDataBase(context: Context): MyDataBase {
            return instance
                ?: Room.databaseBuilder(context.applicationContext, MyDataBase::class.java, "zoom_date_base")
                    .build()
                    .apply { instance = this }
        }
    }
}
