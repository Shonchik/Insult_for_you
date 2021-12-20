package com.example.abuseyou.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.abuseyou.db.model.AbuseModel

@Database(
    entities = [AbuseModel::class],
    version = 2
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun dao(): MyDao

    companion object {

        fun getInstance(context: Context): MyDatabase {
            return Room.databaseBuilder(context, MyDatabase::class.java, "db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}