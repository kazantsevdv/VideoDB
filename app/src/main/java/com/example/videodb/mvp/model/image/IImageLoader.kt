package com.example.videodb.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}