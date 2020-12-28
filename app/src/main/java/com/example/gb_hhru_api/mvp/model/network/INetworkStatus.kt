package com.example.gb_hhru_api.mvp.model.network

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface INetworkStatus{
    fun isOnline(): Observable<Boolean>
    fun isOnlineSingle(): Single<Boolean>
}