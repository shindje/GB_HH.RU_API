package com.example.gb_hhru_api.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.gb_hhru_api.R
import com.example.gb_hhru_api.mvp.entity.Vacancy
import com.example.gb_hhru_api.mvp.model.image.IImageLoader
import com.example.gb_hhru_api.mvp.presenter.VacancyPresenter
import com.example.gb_hhru_api.mvp.view.VacancyView
import com.example.gb_hhru_api.ui.App
import com.example.gb_hhru_api.ui.BackButtonListener
import kotlinx.android.synthetic.main.vacancy.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class VacancyFragment: MvpAppCompatFragment(), VacancyView, BackButtonListener {
    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    companion object {
        fun newInstance(vacancy: Vacancy) = VacancyFragment().apply {
            arguments = Bundle().apply {
                putParcelable("vacancy", vacancy)
            }
        }
    }

    init {
        App.instance.appComponent.inject(this)
    }

    val presenter by moxyPresenter {
        val vacancy: Vacancy? = arguments?.getParcelable("vacancy")
        VacancyPresenter(vacancy).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        View.inflate(context, R.layout.vacancy, null)

    override fun init() {
        val vacancy = presenter.vacancy
        tv_name.text = vacancy?.name
        tv_emloyer_name.text = vacancy?.employer?.name
        tv_salary.text = vacancy?.salary?.toString()
        tv_address.text = vacancy?.address?.raw
        tv_requirement.text = Html.fromHtml(vacancy?.snippet?.requirement, 0)
        tv_responsibility.text = Html.fromHtml(vacancy?.snippet?.responsibility, 0)
        vacancy?.employer?.logoUrls?.original?.apply { imageLoader.loadInto(this, iv_image) }
        btn_vacancy_url.setOnClickListener { presenter.onShowInBrowserClick() }
    }

    override fun backPressed() = presenter.backClick()

    override fun showError(s: String) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show()
    }

    override fun showInBrowser(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

}