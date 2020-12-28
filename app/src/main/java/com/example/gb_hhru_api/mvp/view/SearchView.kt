package com.example.gb_hhru_api.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SearchView: MvpView {
    fun init()
    fun updateVacanciesList()
    fun saveLastSearchText()
}