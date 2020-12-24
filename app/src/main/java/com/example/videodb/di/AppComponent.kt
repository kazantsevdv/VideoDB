package com.example.videodb.di

import com.example.videodb.di.modules.*
import com.example.videodb.mvp.presenter.MainPresenter
import com.example.videodb.mvp.presenter.VideoPresenter
import com.example.videodb.mvp.presenter.VideosPresenter
import com.example.videodb.ui.activity.MainActivity
import com.example.videodb.ui.adapter.VideosRVAdapter
import com.example.videodb.ui.fragment.VideoFragment
import com.example.videodb.ui.fragment.VideosFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        NavigationModule::class,
        RepoModule::class,
        CacheModule::class,
        GlideModule::class
    ]
)
interface AppComponent {
    fun inject(mainPresenter: MainPresenter)
    fun inject(videosPresenter: VideosPresenter)
    fun inject(videoPresenter: VideoPresenter)
    fun inject(videosFragment: VideosFragment)
    fun inject(videoFragment: VideoFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(videosRVAdapter: VideosRVAdapter)

}