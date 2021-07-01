package com.example.gb_hhru_api

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_hhru_api.mvp.entity.Vacancy
import com.example.gb_hhru_api.ui.fragment.VacancyFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VacancyFragmentEspressoTest {
    @Test
    fun fragment_testBundle() {
        //Можно передавать аргументы во Фрагмент, но это необязательно
        val name = "sample name"
        val vacancy = Vacancy("1", name, "url", null, null, null, null, null)
        val fragmentArgs = bundleOf("vacancy" to vacancy)
        //Запускаем Fragment с аргументами
        val scenario = launchFragmentInContainer<VacancyFragment>(fragmentArgs)
        //Возможность менять стейт Фрагмента, но в RESUMED переходит автоматически
        scenario.moveToState(Lifecycle.State.RESUMED)

        val assertion = matches(withText(name))
        onView(withId(R.id.tv_name)).check(assertion)
    }
}