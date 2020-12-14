package com.example.videodb.di

import com.example.videodb.di.modules.*
import com.example.videodb.mvp.presenter.MainPresenter
import com.example.videodb.mvp.presenter.VideosPresenter
import com.example.videodb.ui.activity.MainActivity
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
    fun inject(videosFragment: VideosFragment)
//    fun inject(userRepoPresenter: UserRepoPresenter)
//    fun inject(userRepoFragment: UserRepoFragment)
//
//    //   fun inject(userFragment: UserFragment)
    fun inject(mainActivity: MainActivity)
//    //  fun inject(repositoryFragment: RepositoryFragment)

}