package com.example.gb_hhru_api.mvp.model.cache

import android.content.SharedPreferences

class SharedPreferencesCache(val prefs: SharedPreferences): IPreferencesCache {

    override fun put(name: String, value: String?) {
        val editor = prefs.edit()
        editor.putString(name, value)
        editor.apply()
    }

    override fun get(name: String): String? = prefs.getString(name, null)

    override fun get(name: String, default: String): String {
        return prefs.getString(name, default)!!
    }
}