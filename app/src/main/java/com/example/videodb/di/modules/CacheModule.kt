package com.example.videodb.di.modules

import androidx.room.Room
import com.example.videodb.App
import com.example.videodb.mvp.model.entity.room.db.Database
import com.example.videodb.mvp.model.entity.room.dbcache.IRoomVideoItemCache
import com.example.videodb.mvp.model.entity.room.dbcache.RoomVideoItemCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database =
        Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun videosCache(database: Database): IRoomVideoItemCache = RoomVideoItemCache(database)


}