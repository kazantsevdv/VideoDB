package com.example.videodb.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videodb.App
import com.example.videodb.BackButtonListener
import com.example.videodb.R
import com.example.videodb.mvp.model.image.IImageLoader
import com.example.videodb.mvp.presenter.VideosPresenter
import com.example.videodb.mvp.view.VideosView
import com.example.videodb.ui.adapter.VideosRVAdapter
import kotlinx.android.synthetic.main.fragment_videos.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject


class VideosFragment : MvpAppCompatFragment(), VideosView, BackButtonListener {
    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    companion object {
        fun newInstance() = VideosFragment().apply {
            App.component.inject(this)
        }
    }

    val presenter by moxyPresenter {

        VideosPresenter().apply {
            App.component.inject(this)
        }

    }

    var adapter: VideosRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        View.inflate(context, R.layout.fragment_videos, null)

    override fun init() {
        rv_videos.layoutManager = LinearLayoutManager(requireContext())
        adapter = VideosRVAdapter(
            presenter.videosListPresenter,
            imageLoader
        )


        rv_videos.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}