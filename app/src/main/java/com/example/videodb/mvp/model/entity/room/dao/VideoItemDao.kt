package com.example.videodb.mvp.model.entity.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.videodb.mvp.model.entity.room.RoomVideoItem

@Dao
interface VideoItemDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(videoItem: List<RoomVideoItem>)


    @Query("SELECT * FROM RoomVideoItem")
    fun getAll(): List<RoomVideoItem>

    @Query("SELECT * FROM RoomVideoItem LIMIT :limit OFFSET :offset")
    fun getPage(limit: Int, offset: Int): List<RoomVideoItem>


    @Query("SELECT * FROM RoomVideoItem WHERE id = :id LIMIT 1")
    fun findById(id: Int): RoomVideoItem?

}