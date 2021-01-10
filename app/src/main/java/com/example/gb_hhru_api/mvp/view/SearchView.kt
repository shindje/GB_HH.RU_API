package com.example.gb_hhru_api.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface SearchView: MvpView {
    fun init()
    fun updateVacanciesList()
    fun updatePage(page: String?)
    fun updatePages(pages: String?)
    @OneExecution
    fun showError(s: String)
    @OneExecution
    fun showInBrowser(url: String)
}