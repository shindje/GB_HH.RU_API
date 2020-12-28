package com.example.gb_hhru_api.mvp.model.repo

import com.example.gb_hhru_api.mvp.api.IDataSource
import com.example.gb_hhru_api.mvp.entity.Search
import com.example.gb_hhru_api.mvp.model.cache.IVacanciesCache
import com.example.gb_hhru_api.mvp.model.cache.RoomVacanciesCache
import com.example.gb_hhru_api.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitVacanciesRepo (val api: IDataSource, val networkStatus: INetworkStatus, val vacanciesCache: IVacanciesCache) :
    IVacanciesRepo {
    override fun getVacancies(text: String) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getVacancies(text)
                .flatMap { search ->

                    vacanciesCache.putVacancies(search.items.asList()).andThen(Single.just(search))
                }
        } else {
            var single: Single<Search>? = null
            vacanciesCache.getVacancies().subscribe({
                single = Single.just(Search(it.toTypedArray(), null))
            }, {
                it.printStackTrace()
            })
            return@flatMap single
        }
    }.subscribeOn(Schedulers.io())
}