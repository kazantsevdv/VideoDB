package com.example.videodb.mvp.model.entity.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.videodb.mvp.model.entity.room.RoomVideoItem
import com.example.videodb.mvp.model.entity.room.dao.VideoItemDao

@androidx.room.Database(entities = [RoomVideoItem::class], version = 2)
abstract class Database : RoomDatabase() {
    abstract val userDao: VideoItemDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("Database has not been created")
        fun create(context: Context) {
            instance ?: let {
                instance = Room.databaseBuilder(context, Database::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }

}