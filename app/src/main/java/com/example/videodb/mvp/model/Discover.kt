package com.example.videodb.mvp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Discover(
    @Expose val id: String? = null,
    @Expose val page: Int,
    @Expose val results: List<VideoItem>,
    @Expose val total_pages: Int,
    @Expose val total_results: Int
) : Parcelable