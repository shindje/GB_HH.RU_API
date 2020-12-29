package com.example.gb_hhru_api.mvp.presenter

import com.example.gb_hhru_api.mvp.entity.Search
import com.example.gb_hhru_api.mvp.entity.Vacancy
import com.example.gb_hhru_api.mvp.model.cache.IPreferencesCache
import com.example.gb_hhru_api.mvp.model.network.INetworkStatus
import com.example.gb_hhru_api.mvp.presenter.list.IVacancyListPresenter
import com.example.gb_hhru_api.mvp.model.repo.IVacanciesRepo
import com.example.gb_hhru_api.mvp.view.SearchView
import com.example.gb_hhru_api.mvp.view.list.VacancyItemView
import com.example.gb_hhru_api.navigation.Screens
import com.example.gb_hhru_api.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

const val PAGE = "page"
const val PAGES = "pages"
const val SEARCH_TEXT = "search_text"

class SearchPresenter () : MvpPresenter<SearchView>() {
    @Inject
    lateinit var mainThread: Scheduler
    @Inject
    lateinit var vacanciesRepo: IVacanciesRepo
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var prefs: IPreferencesCache
    @Inject
    lateinit var networkStatus: INetworkStatus
    @Inject
    lateinit var defaultSearchText: String

    lateinit var search: Search
    lateinit var searchText: String

    fun init() {
        search = Search(null, prefs.get(PAGE, "0"), prefs.get(PAGES, "0"))
        searchText = prefs.get(SEARCH_TEXT, defaultSearchText)
    }

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
        }

        override fun getCount() = vacancies.size
    }

    val vacancyListPresenter = VacancyListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData(searchText, search.page)

        vacancyListPresenter.itemClickListener = { view ->
            router.navigateTo(Screens.VacancyScreen(vacancyListPresenter.vacancies[view.pos]))
        }
    }

    fun loadData(text: String, page: String?) {
        vacanciesRepo.getVacancies(text, page, search.pages)
            .observeOn(mainThread)
            .subscribe({ s ->
                search = s
                prefs.put(PAGE, search.page)
                prefs.put(PAGES, search.pages)
                searchText = text
                prefs.put(SEARCH_TEXT, searchText)

                vacancyListPresenter.vacancies.clear()
                s.items?.apply { vacancyListPresenter.vacancies.addAll(this.asList()) }
                viewState.updateVacanciesList()
                viewState.updatePage(((s.page?.toInt() ?: 0) + 1).toString())
                viewState.updatePages(s.pages)
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    fun searchClick(text: String) {
        networkStatus.isOnlineSingle().subscribe { isOnline ->
            if (isOnline) {
                loadData(text, if (text.equals(searchText)) search.page else "0")
            } else
                viewState.showError("Сеть недоступна")
        }
    }

    fun prevClick() {
        networkStatus.isOnlineSingle().subscribe { isOnline ->
            if (isOnline) {
                if (search.page != null && search.page != "0")
                    loadData(searchText, (search.page!!.toInt() - 1).toString())
                else
                    viewState.showError("Показана 1 страница")
            } else
                viewState.showError("Сеть недоступна")
        }
    }

    fun nextClick() {
        networkStatus.isOnlineSingle().subscribe { isOnline ->
            if (isOnline) {
                if (search.page != null && search.page!!.toInt() < (search.pages?:"0").toInt())
                    loadData(searchText, (search.page!!.toInt() + 1).toString())
                else
                    viewState.showError("Показана последняя страница")
            } else
                viewState.showError("Сеть недоступна")
        }

    }
}