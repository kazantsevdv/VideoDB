package com.example.videodb.mvp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoItem(
    @Expose val id: Int,
    @Expose val backdrop_path: String,
    @Expose val original_title: String,
    @Expose val overview: String,
    @Expose val popularity: Double,
    @Expose val poster_path: String,
    @Expose val release_date: String,
    @Expose val vote_average: Double

) : Parcelable