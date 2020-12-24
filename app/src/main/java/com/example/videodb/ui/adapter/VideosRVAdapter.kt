package com.example.videodb.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.videodb.R
import com.example.videodb.mvp.model.image.IImageLoader
import com.example.videodb.mvp.presenter.list.IVideosListPresenter
import com.example.videodb.mvp.view.list.IVideoItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_video.*
import kotlinx.android.synthetic.main.item_video.view.*
import javax.inject.Inject

class VideosRVAdapter(
    val presenter: IVideosListPresenter

) :
    RecyclerView.Adapter<VideosRVAdapter.ViewHolder>() {
    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        ).apply {
            containerView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        IVideoItemView, LayoutContainer {
        override var pos = -1

        override fun setTitle(text: String) = with(containerView) {
            tv_name.text = text
        }

        override fun setRelease(text: String) = with(containerView){
            tv_release.text=text
        }

        override fun loadImage(url: String) {
            imageLoader.loadInto(url, iv_image)
        }
    }

}