package com.example.gb_hhru_api.mvp.presenter

import com.example.gb_hhru_api.mvp.entity.Vacancy
import com.example.gb_hhru_api.mvp.presenter.list.IVacancyListPresenter
import com.example.gb_hhru_api.mvp.model.repo.IVacanciesRepo
import com.example.gb_hhru_api.mvp.view.SearchView
import com.example.gb_hhru_api.mvp.view.list.VacancyItemView
import com.example.gb_hhru_api.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SearchPresenter () : MvpPresenter<SearchView>() {
    @Inject
    lateinit var mainThread: Scheduler
    @Inject
    lateinit var vacanciesRepo: IVacanciesRepo
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var lastSearchText: String

    class VacancyListPresenter : IVacancyListPresenter {
        override var itemClickListener: ((VacancyItemView) -> Unit)? = null

        val vacancies = mutableListOf<Vacancy>()

        override fun bindView(view: VacancyItemView) {
            val vacancy = vacancies[view.pos]
            view.setName(vacancy.name)
            view.setEmployerName(vacancy.employer?.name)
            view.setSalary(vacancy.salary?.toString())
            view.setAddress(vacancy.address?.raw)
            view.setRequirement(vacancy.snippet?.requirementShort())
            view.setResponsibility(vacancy.snippet?.responsibilityShort())
            //user.avatarUrl?.let { view.loadImage(it) }
        }

        override fun getCount() = vacancies.size
    }

    val vacancyListPresenter = VacancyListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData(lastSearchText)

        vacancyListPresenter.itemClickListener = { view ->
            router.navigateTo(Screens.VacancyScreen(vacancyListPresenter.vacancies[view.pos]))
        }
    }

    fun loadData(text: String) {
        vacanciesRepo.getVacancies(text)
            .observeOn(mainThread)
            .subscribe({ search ->
                vacancyListPresenter.vacancies.clear()
                vacancyListPresenter.vacancies.addAll(search.items.asList())
                viewState.updateVacanciesList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    fun searchClick(text: String) {
        viewState.saveLastSearchText()
        loadData(text)
    }
}