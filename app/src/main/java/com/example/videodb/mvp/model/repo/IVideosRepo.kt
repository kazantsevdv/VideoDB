package com.example.videodb.mvp.model.repo

import com.example.videodb.mvp.model.Discover
import com.example.videodb.mvp.model.VideoItem
import io.reactivex.rxjava3.core.Single

interface IVideosRepo {
    fun getVideos(): Single<List<VideoItem>>

}