package com.example.gb_hhru_api.mvp.model.cache

import com.example.gb_hhru_api.mvp.entity.Vacancy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IVacanciesCache {
    fun putVacancies(users: List<Vacancy>?): Completable
    fun getVacancies(): Single<List<Vacancy>>
}