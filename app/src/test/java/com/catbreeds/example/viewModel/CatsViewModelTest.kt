package com.catbreeds.example.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.catbreeds.example.data.repositories.CatBreedsRepositoryImpl
import com.catbreeds.example.data.services.CatBreedService
import com.catbreeds.example.data.services.catbreeds.CatBreedApiServiceHelperImpl
import com.catbreeds.example.domain.repositories.CatBreedsRepository
import com.catbreeds.example.domain.usecase.cats.CatBreedsFetchUseCaseImpl
import com.catbreeds.example.models.catBreedMocks.MockCatBreedsResponse
import com.catbreeds.example.models.catBreedMocks.toCatBreedDataModels
import com.catbreeds.example.models.catBreedMocks.toCatBreedsAPIResponce
import com.catbreeds.example.presentation.ui.features.catbreeds.viewModel.CatsViewModel
import com.catbreeds.example.presentation.ui.features.catbreeds.viewModel.SharedDataRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
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
import kotlin.test.DefaultAsserter.assertEquals


@ExperimentalCoroutinesApi
class CatsViewModelTest {
    private lateinit var mCatsRepo: CatBreedsRepository
    private lateinit var mViewModel: CatsViewModel

    @get:Rule
    val testInstantTaskExecutorRules: TestRule = InstantTaskExecutorRule()

    // Mocking the API service
    private val catService: CatBreedService = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
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

        // Arrange
        val mockCatBreedsResponse = MockCatBreedsResponse()
        val response = toCatBreedsAPIResponce(mockCatBreedsResponse)
        val verifyData = toCatBreedDataModels(mockCatBreedsResponse)

        // Mock API response using MockK
        coEvery { catService.fetchCatBreeds(10) } returns response

        // Verify interactions with the mocked service
        coVerify(exactly = 1) { catService.fetchCatBreeds(10) }
        confirmVerified(catService)
        // Act
        mViewModel.getCatsBreedsData()
        testDispatcher.scheduler.advanceUntilIdle() // Let the coroutine complete

        assertEquals(
            "Both are not same size",
            mViewModel.breedsState.value.catBreeds.size,
            verifyData.size
        )

        assertEquals(
            "Both are not equal",
            mViewModel.breedsState.value.catBreeds[0].breed,
            verifyData[0].breed
        )
    }
}
