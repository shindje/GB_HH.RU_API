package com.example.gb_hhru_api.ui

import android.app.Application
import com.example.gb_hhru_api.R
import com.example.gb_hhru_api.di.AppComponent
import com.example.gb_hhru_api.di.DaggerAppComponent
import com.example.gb_hhru_api.di.modules.AppModule

const val LAST_SEARCH_TEXT_PREF_NAME = "lastSearchText"

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

    fun getLastSearchText(): String {
        val lastSearchText = applicationContext
            .getSharedPreferences("app", MODE_PRIVATE)
            .getString(LAST_SEARCH_TEXT_PREF_NAME, null)
        return lastSearchText ?: getString(R.string.default_search_text)
    }

    fun setLastSearchText(text: String) {
        var editor =  applicationContext
            .getSharedPreferences("app", MODE_PRIVATE)
            .edit()
        editor.putString(LAST_SEARCH_TEXT_PREF_NAME, text)
        editor.apply()
    }
}