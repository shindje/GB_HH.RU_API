package com.example.gb_hhru_api.ui.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.gb_hhru_api.R
import com.example.gb_hhru_api.mvp.model.image.IImageLoader
import com.example.gb_hhru_api.mvp.presenter.list.IVacancyListPresenter
import com.example.gb_hhru_api.mvp.view.list.VacancyItemView
import com.example.gb_hhru_api.ui.App
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_vacancy.*
import javax.inject.Inject

class VacanciesRvAdapter(val presenter: IVacancyListPresenter) : RecyclerView.Adapter<VacanciesRvAdapter.ViewHolder>() {
    init {
        App.instance.appComponent.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_vacancy, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener { presenter.itemClickListener?.invoke(holder) }
        presenter.bindView(holder)
    }

    override fun getItemCount() = presenter.getCount()

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        VacancyItemView, LayoutContainer {
        override var pos = -1

        override fun setName(name: String) {
            tv_item_name.text = name
        }

        override fun setEmployerName(name: String?) {
            tv_item_emloyer_name.text = name
        }

        override fun setSalary(salary: String?) {
            tv_item_salary.text = salary
        }

        override fun setAddress(address: String?) {
            tv_item_address.text = address
        }

        override fun setRequirement(requirement: String?) {
            tv_item_requirement_short.text = Html.fromHtml(requirement, 0)
        }

        override fun setResponsibility(responsibility: String?) {
            tv_item_responsibility_short.text = Html.fromHtml(responsibility, 0)
        }
    }

}