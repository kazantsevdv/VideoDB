package com.example.videodb.mvp.model.entity.room.dbcache

import com.example.videodb.mvp.model.VideoItem
import com.example.videodb.mvp.model.entity.room.RoomVideoItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import com.example.videodb.mvp.model.entity.room.db.Database

class RoomVideoItemCache(val db: Database) : IRoomVideoItemCache {
    override fun put(users: List<VideoItem>) = Completable.fromCallable {
        val roomUsers = users.map { item ->
            RoomVideoItem(
                item.id,
                item.backdrop_path ?: "",
                item.original_title ?: "",
                item.overview ?: "",
                item.popularity,
                item.poster_path,
                item.release_date,
                item.vote_average,
            )
        }
        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())

    override fun getAll() = Single.fromCallable {
        db.userDao.getAll().map { roomItem ->
            VideoItem(
                roomItem.id, roomItem.backdrop_path, roomItem.original_title,
                roomItem.overview, roomItem.popularity, roomItem.poster_path,
                roomItem.release_date, roomItem.vote_average
            )
        }
    }.subscribeOn(Schedulers.io())
}
