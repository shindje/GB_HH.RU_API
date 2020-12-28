package com.example.gb_hhru_api.navigation

import com.example.gb_hhru_api.mvp.entity.Vacancy
import com.example.gb_hhru_api.ui.fragment.SearchFragment
import com.example.gb_hhru_api.ui.fragment.VacancyFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class SearchScreen() : SupportAppScreen() {
        override fun getFragment() = SearchFragment.newInstance()
    }

    class VacancyScreen(val vacancy: Vacancy) : SupportAppScreen() {
        override fun getFragment() = VacancyFragment.newInstance(vacancy)
    }
}