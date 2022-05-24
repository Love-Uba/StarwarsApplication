package com.loveuba.starwarsapplication.common.starwarspresentation.detailspresentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.loveuba.starwarsapplication.common.domain.repository.FakeStarWarsRepository
import com.loveuba.starwarsapplication.data.repository.DetailsUseCase
import com.loveuba.starwarsapplication.data.repository.IStarwarsRepository
import com.loveuba.starwarsapplication.viewmodel.DetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {

    @get: Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var starwarsRepository: IStarwarsRepository
    private lateinit var detailsUseCase: DetailsUseCase

    @Before
    fun setUp() {
        starwarsRepository = FakeStarWarsRepository()
        detailsUseCase = DetailsUseCase(starwarsRepository)
        detailsViewModel = DetailsViewModel(detailsUseCase)
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

//    @ExperimentalCoroutinesApi
//    @Test
//    fun `get user planet as a stateflow of specie result`() = runTest {
//        detailsViewModel.actionGetPlanet("Naboo").join()
//        val planetResult = detailsViewModel.getPlanetResult.first()
//        Assert.assertEquals("Naboo", planetResult.data?.name)
//
//    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun `get selected specie as a stateflow of specie result`() = runTest {
//        detailsViewModel.actionGetSpecies("").join()
//        val specieResult = detailsViewModel.getSpeciesResult.first()
//        Assert.assertEquals("Zabrak", specieResult.data?.name)
//    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get films of a selected character as a stateflow of film results`() = runTest {
        detailsViewModel.actionGetFilms().join()

        val filmResult = detailsViewModel.getFilmResult.first()

        Assert.assertEquals("A New Hope", filmResult.data?.get(0)?.title)
    }

}