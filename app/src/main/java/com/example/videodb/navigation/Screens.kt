package com.example.videodb.navigation

import com.example.videodb.mvp.model.VideoItem
import com.example.videodb.ui.fragment.VideoFragment
import com.example.videodb.ui.fragment.VideosFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class VideosScreen() : SupportAppScreen() {
        override fun getFragment() = VideosFragment.newInstance()
    }

    class VideoScreen(private val video: VideoItem) : SupportAppScreen() {
        override fun getFragment() = VideoFragment.newInstance(video)
    }
//    class UserRepoScreen(private val user:Discover) : SupportAppScreen() {
//        override fun getFragment() = UserRepoFragment.newInstance(user)
//    }


}