package com.example.videodb.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.videodb.App
import com.example.videodb.BackButtonListener
import com.example.videodb.R
import com.example.videodb.mvp.model.VideoItem
import com.example.videodb.mvp.model.image.IImageLoader
import com.example.videodb.mvp.presenter.VideoPresenter
import com.example.videodb.mvp.view.VideoView
import kotlinx.android.synthetic.main.fragment_video.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class VideoFragment : MvpAppCompatFragment(), VideoView, BackButtonListener {
    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    companion object {
        private const val VIDEO_ARG = "video"
        fun newInstance(video: VideoItem) = VideoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(VIDEO_ARG, video)
            }

        }
    }

    init {
        App.component.inject(this)
    }

    val presenter by moxyPresenter {
        val repository = arguments?.getParcelable<VideoItem>(VIDEO_ARG) as VideoItem
        VideoPresenter(repository).apply { App.component.inject(this) }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        View.inflate(context, R.layout.fragment_video, null)


    override fun backPressed() = presenter.backClick()
    override fun setTitle(text: String) {
        title.text = text
    }

    override fun setOverview(text: String) {
        overview.text = text
    }

    override fun setRelease(text: String) {
    }

    override fun loadImage(url: String) {
        imageLoader.loadInto(url, image)
    }

}