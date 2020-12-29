package com.example.gb_hhru_api.mvp.model.repo

import com.example.gb_hhru_api.mvp.entity.Search
import com.example.gb_hhru_api.mvp.entity.Vacancy
import io.reactivex.rxjava3.core.Single

interface IVacanciesRepo {
    fun getVacancies(text: String, page: String?, pages: String?): Single<Search>
}