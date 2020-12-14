package com.example.videodb.mvp.model.repo

import com.example.videodb.mvp.model.api.IDataSource
import com.example.videodb.mvp.model.entity.room.dbcache.IRoomVideoItemCache
import com.example.videodb.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitVideosRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cache: IRoomVideoItemCache
) : IVideosRepo {
    override fun getVideos() = networkStatus.inOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getMoves().flatMap { discover ->
                cache.put(discover.results).andThen(Single.just(discover.results))
            }
        } else {
            cache.getAll()
        }

    }.subscribeOn(Schedulers.io())
}
