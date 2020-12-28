package com.example.gb_hhru_api.mvp.api

import com.example.gb_hhru_api.mvp.entity.Search
import com.example.gb_hhru_api.mvp.entity.Vacancy
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface IDataSource {
//    @GET("users")
//    fun getUsers(): Single<List<GithubUser>>
//
//    @GET("users/{login}")
//    fun getUser(@Path("login") login: String): Single<GithubUser>
//
//    @GET
//    fun getUserRepos(@Url url: String?): Single<List<GithubRepository>>

    /*@POST
    fun getAPIKey*/

    @GET ("vacancies")
    fun getVacancies(@Query("text") text: String): Single<Search>
}