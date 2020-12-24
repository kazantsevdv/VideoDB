package com.example.videodb.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface VideoView : MvpView {
    fun setTitle(text: String)
    fun setOverview(text: String)
    fun setRelease(text: String)
    fun loadImage(url: String)

}