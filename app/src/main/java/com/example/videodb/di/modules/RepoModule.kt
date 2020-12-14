package com.example.videodb.di.modules

import com.example.videodb.mvp.model.api.IDataSource
import com.example.videodb.mvp.model.entity.room.dbcache.IRoomVideoItemCache
import com.example.videodb.mvp.model.network.INetworkStatus
import com.example.videodb.mvp.model.repo.IVideosRepo
import com.example.videodb.mvp.model.repo.RetrofitVideosRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun videosRepo(
        api: IDataSource,
        cache: IRoomVideoItemCache,
        networkStatus: INetworkStatus
    ): IVideosRepo = RetrofitVideosRepo(api, networkStatus, cache)

}