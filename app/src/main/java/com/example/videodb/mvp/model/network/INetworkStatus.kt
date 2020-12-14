package com.example.videodb.mvp.model.network

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface INetworkStatus {
    fun isOnline(): Observable<Boolean>
    fun inOnlineSingle(): Single<Boolean>
}