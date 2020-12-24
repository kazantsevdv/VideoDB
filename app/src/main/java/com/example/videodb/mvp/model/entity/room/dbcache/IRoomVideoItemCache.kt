package com.example.videodb.mvp.model.entity.room.dbcache

import com.example.videodb.mvp.model.VideoItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IRoomVideoItemCache {
    fun put(users: List<VideoItem>): Completable
    fun getAll(): Single<List<VideoItem>>
    fun getPage(offset: Int): Single<List<VideoItem>>
}