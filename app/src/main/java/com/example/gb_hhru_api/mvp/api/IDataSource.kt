package com.example.gb_hhru_api.mvp.api

import com.example.gb_hhru_api.mvp.entity.Search
import com.example.gb_hhru_api.mvp.entity.Vacancy
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface IDataSource {
    @GET ("vacancies")
    fun getVacancies(@Query("text") text: String, @Query("page") page: String): Single<Search>
}