package com.example.gb_hhru_api.mvp.presenter

import com.example.gb_hhru_api.mvp.entity.Vacancy
import com.example.gb_hhru_api.mvp.view.VacancyView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class VacancyPresenter (val vacancy: Vacancy?) : MvpPresenter<VacancyView>() {
    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    fun onShowInBrowserClick() {
        if (vacancy?.alternateUrl != null)
            viewState.showInBrowser(vacancy.alternateUrl)
        else
            viewState.showError("URL пуст")
    }
}