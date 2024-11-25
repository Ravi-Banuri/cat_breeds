package com.catbreeds.example.viewModel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.catbreeds.example.R
import com.catbreeds.example.domain.repositories.CatBreedsRepositoryImpl
import com.catbreeds.example.data.services.CatBreedService
import com.catbreeds.example.data.services.catbreeds.CatBreedApiServiceHelperImpl
import com.catbreeds.example.domain.repositories.CatBreedsRepository
import com.catbreeds.example.domain.usecase.cats.CatBreedsFetchUseCaseImpl
import com.catbreeds.example.models.catBreedMocks.MockCatBreedsResponse
import com.catbreeds.example.models.catBreedMocks.toCatBreedDataModels
import com.catbreeds.example.models.catBreedMocks.toCatBreedsAPIResponce
import com.catbreeds.example.presentation.ui.features.catbreeds.viewModel.CatsViewModel
import com.catbreeds.example.presentation.ui.features.catbreeds.viewModel.SharedDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.DefaultAsserter.assertEquals


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CatsViewModelTest {
    private lateinit var mCatsRepo: CatBreedsRepository
    private val application: Application = mock()
    private lateinit var mViewModel: CatsViewModel

    @get:Rule
    val testInstantTaskExecutorRules: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var catService: CatBreedService

    private val testDispatcher = StandardTestDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        val apiHelper = CatBreedApiServiceHelperImpl(catService)
        mCatsRepo = CatBreedsRepositoryImpl(apiHelper)
        Dispatchers.setMain(testDispatcher)
        val catbreedUseCase = CatBreedsFetchUseCaseImpl(mCatsRepo)

        val sharedRepo = SharedDataRepository()

        mViewModel = CatsViewModel(catbreedUseCase, sharedRepo)
    }

    @Test
    fun testGetCatsBreedsApiData() = runTest(UnconfinedTestDispatcher()) {
        Dispatchers.setMain(Dispatchers.Unconfined)
        val mockCatBreedsResponse = MockCatBreedsResponse()
        val response = toCatBreedsAPIResponce(mockCatBreedsResponse)
        val verifyData = toCatBreedDataModels(mockCatBreedsResponse)
        `when`(catService.fetchCatBreeds(10)).thenReturn(response)// Mock the API response
        verify(catService).fetchCatBreeds(10)
        mViewModel.getCatsBreedsData()
        testDispatcher.scheduler.advanceUntilIdle() // Let the coroutine complete and changes propagate
        val result = mViewModel.breedsState.value.catBreeds
        assertEquals(
            application.getString(R.string.both_are_not_equal),
            result.size,
            verifyData.size
        )

        assertEquals(
            application.getString(R.string.both_are_not_equal),
            result[0].breed,
            verifyData[0].breed
        )
    }
}