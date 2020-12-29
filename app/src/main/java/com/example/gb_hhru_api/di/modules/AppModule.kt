package com.example.gb_hhru_api.di.modules

import com.example.gb_hhru_api.mvp.model.cache.IPreferencesCache
import com.example.gb_hhru_api.ui.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }

    @Provides
    fun mainThread(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    fun prefencesCache(): IPreferencesCache = app.getSharedPreferences()

    @Provides
    fun defaultSearchText(): String = app.getDefaultSearchText()
}