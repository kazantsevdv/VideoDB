package com.example.videodb.navigation

import com.example.videodb.ui.fragment.VideosFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class VideosScreen() : SupportAppScreen() {
        override fun getFragment() = VideosFragment.newInstance()
    }
//    class UserScreen(private val user:GithubUser) : SupportAppScreen() {
//        override fun getFragment() = UserFragment.newInstance(user)
//    }
//    class UserRepoScreen(private val user:Discover) : SupportAppScreen() {
//        override fun getFragment() = UserRepoFragment.newInstance(user)
//    }


}