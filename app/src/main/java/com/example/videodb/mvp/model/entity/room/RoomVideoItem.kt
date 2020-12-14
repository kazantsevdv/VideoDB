package com.example.videodb.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomVideoItem(

 @PrimaryKey val id: Int,
 val backdrop_path: String,
 val original_title: String,
 val overview: String,
 val popularity: Double,
 val poster_path: String,
 val release_date: String,
 val vote_average: Double
)