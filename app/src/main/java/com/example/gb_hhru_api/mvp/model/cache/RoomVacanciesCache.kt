package com.example.gb_hhru_api.mvp.model.cache

import com.example.gb_hhru_api.mvp.entity.*
import com.example.gb_hhru_api.mvp.entity.room.RoomVacancy
import com.example.gb_hhru_api.mvp.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomVacanciesCache(val db: Database): IVacanciesCache {

    override fun putVacancies(vacancies: List<Vacancy>?) =
        Completable.fromCallable {
            val roomVacancys = vacancies?.map { vacancy -> RoomVacancy(
                vacancy.id ?: "", vacancy.name ?: "", vacancy.url,
                vacancy.salary?.from, vacancy.salary?.to, vacancy.salary?.currency, vacancy.address?.raw,
                vacancy.employer?.id, vacancy.employer?.name,
                vacancy.employer?.logoUrls?.original, vacancy.employer?.logoUrls?.size90, vacancy.employer?.logoUrls?.size240,
                vacancy.snippet?.requirement, vacancy.snippet?.responsibility, vacancy.alternateUrl)}
            db.vacancyDao.deleteAll()
            roomVacancys?.apply { db.vacancyDao.insert(this) }
            vacancies
        }

    override fun getVacancies() =
        Single.fromCallable {
            db.vacancyDao.getAll().map { roomVacancy ->
                Vacancy(roomVacancy.id, roomVacancy.name, roomVacancy.url?: "", Salary(roomVacancy.salaryFrom, roomVacancy.salaryTo, roomVacancy.salaryCurrency),
                    Employer(roomVacancy.employerId, roomVacancy.employerName, LogoUrl(roomVacancy.employerLogoUrlOriginal, roomVacancy.employerLogoUrlSize90, roomVacancy.employerLogoUrlSize240)),
                    Address(roomVacancy.addressRaw), Snippet(roomVacancy.requirement, roomVacancy.responsibility), roomVacancy.alternateUrl
                )
            }
        }
}