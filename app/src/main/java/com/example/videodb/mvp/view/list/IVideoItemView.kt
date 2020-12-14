package com.example.videodb.mvp.view.list

interface IVideoItemView : IItemView {
    fun setTitle(text: String)
    fun setRelease(text: String)
    fun loadImage(url: String)
}