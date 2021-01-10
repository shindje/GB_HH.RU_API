package com.example.gb_hhru_api.mvp.view.list

import java.net.Inet4Address

interface VacancyItemView : IItemView {
    fun setName(name: String)
    fun setEmployerName(name: String?)
    fun setSalary(salary: String?)
    fun setAddress(address: String?)
    fun setRequirement(requirement: String?)
    fun setResponsibility(responsibility: String?)
}