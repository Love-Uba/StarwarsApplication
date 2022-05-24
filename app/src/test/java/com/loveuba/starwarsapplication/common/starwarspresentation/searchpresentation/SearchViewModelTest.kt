package com.loveuba.starwarsapplication.common.starwarspresentation.searchpresentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.loveuba.starwarsapplication.common.domain.repository.FakeStarWarsRepository
import com.loveuba.starwarsapplication.data.repository.IStarwarsRepository
import com.loveuba.starwarsapplication.data.repository.SearchUseCase
import com.loveuba.starwarsapplication.viewmodel.StarwarsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @get: Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var searchViewModel: StarwarsViewModel
    private lateinit var starwarsRepository: IStarwarsRepository
    private lateinit var searchUseCase: SearchUseCase

    @Before
    fun setUp() {
        starwarsRepository = FakeStarWarsRepository()
        searchUseCase = SearchUseCase(starwarsRepository)
        searchViewModel = StarwarsViewModel(searchUseCase)
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get search to return a stateflow of searched results`() = runTest {
        searchViewModel.actionSearch("yod").join()

        val searchResult =  searchViewModel.getSearchResult.first()

        Assert.assertEquals("Yoda", searchResult.data?.get(0)?.name)
    }
}