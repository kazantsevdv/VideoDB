package com.example.videodb.mvp.presenter

import com.example.videodb.mvp.model.VideoItem
import com.example.videodb.mvp.model.repo.IVideosRepo
import com.example.videodb.mvp.presenter.list.IVideosListPresenter
import com.example.videodb.mvp.view.VideosView
import com.example.videodb.mvp.view.list.IVideoItemView
import com.example.videodb.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class VideosPresenter() : MvpPresenter<VideosView>() {
    @Inject
    lateinit var videosRepo: IVideosRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var scheduler: Scheduler


    class VideosListPresenter : IVideosListPresenter {
        override var itemClickListener: ((IVideoItemView) -> Unit)? = null

        val videos = mutableListOf<VideoItem>()

        override fun bindView(view: IVideoItemView) {
            val video = videos[view.pos]
            video.original_title.let { view.setTitle(it) }
            video.release_date.let { view.setRelease(it) }
            video.poster_path.let { view.loadImage(it) }
        }

        override fun getCount() = videos.size
    }

    val videosListPresenter = VideosListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        videosListPresenter.itemClickListener = { view ->
            router.navigateTo(
                Screens.VideoScreen(
                    videosListPresenter.videos[view.pos]
                )
            )
        }
    }

    fun loadData() {
        videosRepo.getVideos()
            .observeOn(scheduler)
            .subscribe({ videos ->
                //videosListPresenter.videos.clear()
                videosListPresenter.videos.addAll(videos)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}