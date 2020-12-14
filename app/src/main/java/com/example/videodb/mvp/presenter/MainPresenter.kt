package com.example.videodb.mvp.presenter

import com.example.videodb.navigation.Screens
import moxy.MvpPresenter
import com.example.videodb.mvp.view.MainView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {
    @Inject
    lateinit var router: Router
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.VideosScreen())
    }

    fun backClick() {
        router.exit()
    }

}