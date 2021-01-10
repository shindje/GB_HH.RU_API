package com.example.gb_hhru_api.mvp.model.cache

interface IPreferencesCache {
    fun put(name: String, value: String?)
    fun get(name: String): String?
    fun get(name: String, default: String): String
}