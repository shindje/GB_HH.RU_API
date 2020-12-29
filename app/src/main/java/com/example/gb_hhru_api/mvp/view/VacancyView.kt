package com.example.gb_hhru_api.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface VacancyView: MvpView {
    fun init()
    @OneExecution
    fun showInBrowser(url: String)
    @OneExecution
    fun showError(s: String)
}