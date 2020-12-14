package com.example.videodb.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface VideosView : MvpView {
    fun init()
    fun updateList()
}