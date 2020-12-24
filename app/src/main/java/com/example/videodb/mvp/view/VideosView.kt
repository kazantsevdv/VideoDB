package com.example.videodb.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

//@SkipStrategy
@StateStrategyType(AddToEndStrategy::class)
interface VideosView : MvpView {
    fun init()
    fun updateList()
}