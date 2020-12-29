package com.example.gb_hhru_api.ui

import android.app.Application
import com.example.gb_hhru_api.R
import com.example.gb_hhru_api.di.AppComponent
import com.example.gb_hhru_api.di.DaggerAppComponent
import com.example.gb_hhru_api.di.modules.AppModule
import com.example.gb_hhru_api.mvp.model.cache.IPreferencesCache
import com.example.gb_hhru_api.mvp.model.cache.SharedPreferencesCache

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getDefaultSearchText(): String {
        return getString(R.string.default_search_text)
    }

    fun getSharedPreferences(): IPreferencesCache {
        return SharedPreferencesCache(applicationContext.getSharedPreferences("app", MODE_PRIVATE));
    }
}