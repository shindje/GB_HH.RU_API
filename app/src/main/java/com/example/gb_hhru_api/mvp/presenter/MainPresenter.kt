package com.example.gb_hhru_api.mvp.presenter

import com.example.gb_hhru_api.mvp.view.MainView
import com.example.gb_hhru_api.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter(): MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.SearchScreen())
    }

    fun backClick() {
        router.exit()
    }

}