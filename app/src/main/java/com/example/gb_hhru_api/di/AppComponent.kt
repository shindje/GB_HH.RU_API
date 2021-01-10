package com.example.gb_hhru_api.di

import com.example.gb_hhru_api.di.modules.*
import com.example.gb_hhru_api.mvp.presenter.MainPresenter
import com.example.gb_hhru_api.mvp.presenter.SearchPresenter
import com.example.gb_hhru_api.mvp.presenter.VacancyPresenter
import com.example.gb_hhru_api.ui.MainActivity
import com.example.gb_hhru_api.ui.adapter.VacanciesRvAdapter
import com.example.gb_hhru_api.ui.fragment.VacancyFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class,
        ImageLoaderModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(searchPresenter: SearchPresenter)
    fun inject(vacanciesRvAdapter: VacanciesRvAdapter)
    fun inject(vacancyPresenter: VacancyPresenter)
    fun inject(vacancyFragment: VacancyFragment)
}