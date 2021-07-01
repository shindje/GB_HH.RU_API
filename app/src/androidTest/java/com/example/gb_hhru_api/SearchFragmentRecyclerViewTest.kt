package com.example.gb_hhru_api

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.gb_hhru_api.ui.adapter.VacanciesRvAdapter
import com.example.gb_hhru_api.ui.fragment.SearchFragment
import org.junit.Before
import org.junit.Test

class SearchFragmentRecyclerViewTest {
    private lateinit var scenario: FragmentScenario<SearchFragment>

    @Before
    fun setup() {
        //Запускаем Fragment в корне Activity
        scenario = launchFragmentInContainer()
    }

    @Test
    fun searchFragment_ScrollTo() {
        onView(withId(R.id.btn_search)).perform(click())
        onView(isRoot()).perform(delay())

        onView(withId(R.id.rv_vacancies))
            .perform(RecyclerViewActions.scrollToPosition<VacanciesRvAdapter.ViewHolder>(10))
    }

    @Test
    fun searchFragment_PerformClickAtPosition() {
        onView(withId(R.id.btn_search)).perform(click())
        onView(isRoot()).perform(delay())

        onView(withId(R.id.rv_vacancies))
            .perform(RecyclerViewActions.scrollToPosition<VacanciesRvAdapter.ViewHolder>(11))

        onView(withId(R.id.rv_vacancies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<VacanciesRvAdapter.ViewHolder>(
                    10,
                    click()
                )
            )
    }

}