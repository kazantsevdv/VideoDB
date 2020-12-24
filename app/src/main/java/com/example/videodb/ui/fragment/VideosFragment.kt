package com.example.videodb.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.videodb.App
import com.example.videodb.BackButtonListener
import com.example.videodb.R
import com.example.videodb.mvp.presenter.VideosPresenter
import com.example.videodb.mvp.view.VideosView
import com.example.videodb.ui.adapter.VideosRVAdapter
import kotlinx.android.synthetic.main.fragment_videos.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class VideosFragment : MvpAppCompatFragment(), VideosView, BackButtonListener {


    companion object {
        fun newInstance() = VideosFragment().apply {

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
        App.component.inject(this)
        val mLayoutManager = LinearLayoutManager(requireContext())
        rv_videos.layoutManager = mLayoutManager
        adapter = VideosRVAdapter(
            presenter.videosListPresenter
        ).apply {
            App.component.inject(this)
        }
        var pastVisiblesItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int
        rv_videos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    visibleItemCount = mLayoutManager.childCount
                    totalItemCount = mLayoutManager.itemCount
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        presenter.loadData()
                    }
                }
            }
        })
        adapter!!.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        rv_videos.adapter = adapter
    }


    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}