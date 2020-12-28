package com.example.gb_hhru_api.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}