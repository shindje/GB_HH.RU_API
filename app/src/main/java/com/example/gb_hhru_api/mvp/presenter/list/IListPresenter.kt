package com.example.gb_hhru_api.mvp.presenter.list

import com.example.gb_hhru_api.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}