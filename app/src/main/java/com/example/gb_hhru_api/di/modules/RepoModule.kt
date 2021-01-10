package com.example.gb_hhru_api.di.modules

import com.example.gb_hhru_api.mvp.api.IDataSource
import com.example.gb_hhru_api.mvp.model.cache.IVacanciesCache
import com.example.gb_hhru_api.mvp.model.network.INetworkStatus
import com.example.gb_hhru_api.mvp.model.repo.IVacanciesRepo
import com.example.gb_hhru_api.mvp.model.repo.RetrofitVacanciesRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun vacanciesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IVacanciesCache): IVacanciesRepo = RetrofitVacanciesRepo(api, networkStatus, cache)
}