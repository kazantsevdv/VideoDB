package com.example.videodb.mvp.model.api

import com.example.videodb.mvp.model.Discover
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataSource {

    @GET("discover/movie")
    fun getMoves(): Single<Discover>

//    @GET("users/{login}")
//    fun getUser(@Path("login") login: String): Single<Discover>
//
//    @GET
//    fun getUserRepos(@Url url: String): Single<List<VideoItem>>

}
