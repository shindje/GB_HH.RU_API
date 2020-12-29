package com.example.gb_hhru_api.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            this.init()
        }
    }

    var adapter: VacanciesRvAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.fragment_search, null)

    override fun init() {
        et_search_text.setText(presenter.defaultSearchText)
        rv_vacancies.layoutManager = LinearLayoutManager(requireContext())
        adapter = VacanciesRvAdapter(presenter.vacancyListPresenter)
        rv_vacancies.adapter = adapter
        btn_search.setOnClickListener { presenter.searchClick(et_search_text.text.toString(), tv_page.text.toString()) }
        btn_prev.setOnClickListener { presenter.prevClick(et_search_text.text.toString()) }
        btn_next.setOnClickListener { presenter.nextClick(et_search_text.text.toString()) }
    }

    override fun updateVacanciesList() {
        adapter?.notifyDataSetChanged()
    }

    override fun updatePage(page: String?) {
        tv_page.text = page
    }

    override fun updatePages(pages: String?) {
        tv_max_page.text = pages
    }

    override fun showError(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    override fun backPressed() = presenter.backClick()
}