package com.example.gb_hhru_api

import com.example.gb_hhru_api.mvp.entity.Search
import com.example.gb_hhru_api.mvp.model.cache.IPreferencesCache
import com.example.gb_hhru_api.mvp.model.network.INetworkStatus
import com.example.gb_hhru_api.mvp.model.repo.IVacanciesRepo
import com.example.gb_hhru_api.mvp.presenter.PAGE
import com.example.gb_hhru_api.mvp.presenter.PAGES
import com.example.gb_hhru_api.mvp.presenter.SearchPresenter
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class SearchPresenterTest {
    private lateinit var presenter: SearchPresenter

    @Mock
    private lateinit var vacanciesRepo: IVacanciesRepo
    @Mock
    private lateinit var networkStatus: INetworkStatus
    @Mock
    private lateinit var prefs: IPreferencesCache

    fun <T> eq(obj: T): T = Mockito.eq<T>(obj)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        `when`(vacanciesRepo.getVacancies(anyString(), anyString(), anyString(), anyString())).thenReturn(Single.just(
            Search(null, "", "", "")
        ))
        `when`(networkStatus.isOnlineSingle()).thenReturn(Single.just(true))
        `when`(prefs.get(anyString(), anyString())).thenReturn("")
        `when`(prefs.get(eq(PAGE), anyString())).thenReturn("1")
        `when`(prefs.get(eq(PAGES), anyString())).thenReturn("2")
        `when`(prefs.get(anyString())).thenReturn("")

        presenter = SearchPresenter(TestScheduler(), vacanciesRepo, networkStatus, prefs, "")
        presenter.init()
    }

    @Test
    fun searchClick_Test() {
        presenter.searchClick("")
        verify(vacanciesRepo, times(1)).getVacancies(
            anyString(),
            anyString(),
            anyString(),
            anyString()
        )
    }

    @Test
    fun prevClick_Test() {
        presenter.prevClick()
        verify(vacanciesRepo, times(1)).getVacancies(
            anyString(),
            anyString(),
            anyString(),
            anyString()
        )
    }

    @Test
    fun nextClick_Test() {
        presenter.nextClick()
        verify(vacanciesRepo, times(1)).getVacancies(
            anyString(),
            anyString(),
            anyString(),
            anyString()
        )
    }
}