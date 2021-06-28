package com.example.gb_hhru_api

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_hhru_api.ui.fragment.SearchFragment
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchFragmentEspressoTest {
    private lateinit var scenario: FragmentScenario<SearchFragment>

    @Before
    fun setup() {
        //Запускаем Fragment в корне Activity
        scenario = launchFragmentInContainer()
    }

    @Test
    fun fragment_testUpdatePage() {
        scenario.onFragment { fragment ->
            fragment.updatePage("10")
        }
        val assertion = matches(withText("10"))
        onView(withId(R.id.tv_page)).check(assertion)
    }

    @Test
    fun fragment_testUpdatePages() {
        scenario.onFragment { fragment ->
            fragment.updatePages("100")
        }
        val assertion = matches(withText("100"))
        onView(withId(R.id.tv_max_page)).check(assertion)
    }

    @Test
    fun fragment_testSearch() {
        scenario.onFragment { fragment ->
            fragment.updatePage("10")
        }
        onView(withId(R.id.btn_search)).perform(click())
        onView(ViewMatchers.isRoot()).perform(delay())

        onView(withId(R.id.tv_page)).check(matches(withText("1")))
    }


}

fun delay(): ViewAction? {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()
        override fun getDescription(): String = "wait for $2 seconds"
        override fun perform(uiController: UiController, v: View?) {
            uiController.loopMainThreadForAtLeast(2000)
        }
    }
}