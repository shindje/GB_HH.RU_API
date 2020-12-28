package com.example.gb_hhru_api.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gb_hhru_api.R
import com.example.gb_hhru_api.mvp.presenter.SearchPresenter
import com.example.gb_hhru_api.mvp.view.SearchView
import com.example.gb_hhru_api.ui.App
import com.example.gb_hhru_api.ui.BackButtonListener
import com.example.gb_hhru_api.ui.adapter.VacanciesRvAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class SearchFragment :  MvpAppCompatFragment(), SearchView, BackButtonListener {

    companion object {
        fun newInstance() = SearchFragment()
    }

    val presenter by moxyPresenter {
        SearchPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    var adapter: VacanciesRvAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_search, null)

    override fun init() {
        tv_search_text.setText(App.instance.getLastSearchText())
        rv_vacancies.layoutManager = LinearLayoutManager(requireContext())
        adapter = VacanciesRvAdapter(presenter.vacancyListPresenter)
        rv_vacancies.adapter = adapter
        btn_search.setOnClickListener { presenter.searchClick(tv_search_text.text.toString()) }
    }

    override fun updateVacanciesList() {
        adapter?.notifyDataSetChanged()
    }

    override fun saveLastSearchText() {
        App.instance.setLastSearchText(tv_search_text.text.toString())
    }

    override fun backPressed() = presenter.backClick()
}