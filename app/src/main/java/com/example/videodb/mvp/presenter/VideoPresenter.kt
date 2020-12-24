package com.example.videodb.mvp.presenter

import com.example.videodb.mvp.model.VideoItem
import com.example.videodb.mvp.view.VideoView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class VideoPresenter(val video: VideoItem) : MvpPresenter<VideoView>() {

    @Inject
    lateinit var router: Router


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setTitle(video.original_title)
        viewState.loadImage(video.poster_path)
        viewState.setOverview(video.overview)


    }


    fun backClick(): Boolean {
        router.exit()
        return true
    }
}