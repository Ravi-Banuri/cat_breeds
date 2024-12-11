package com.catbreeds.example.domain.usecase.cats

import com.catbreeds.example.data.NetworkResult
import com.catbreeds.example.data.models.catBreedData.CatBreedsResponse
import com.catbreeds.example.domain.repositories.CatBreedsRepository
import com.catbreeds.example.models.catBreedMocks.MockCatBreedsResponse
import com.catbreeds.example.models.catBreedMocks.toCatBreedDataModels
import com.catbreeds.example.models.catBreedMocks.toCatBreedsRepoResponce
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class CatBreedsFetchUseCaseImplTest {

    private lateinit var useCase: CatBreedsFetchUseCaseImpl
    private val repository: CatBreedsRepository = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        useCase = CatBreedsFetchUseCaseImpl(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testExecuteFetchCatBreeds() = runTest {
        // Arrange
        val mockCatBreedsResponse = MockCatBreedsResponse() // Mock API response
        val catBreedsDataList = toCatBreedsRepoResponce(mockCatBreedsResponse)//mockCatBreedsResponse.data.map { it.mapCatBreedsDataItems() }
        val verifyData = toCatBreedDataModels(mockCatBreedsResponse)

        val repositoryFlow = flow {
            emit(NetworkResult.Loading()) // First emit Loading
            emit(NetworkResult.Success(catBreedsDataList)) // Then emit Success
        }
        coEvery { repository.fetchCatBreeds() } returns repositoryFlow

        // Act
        val result = useCase.executeFetchCatBreeds().toList()

        // Assert
        assertTrue(result.first() is NetworkResult.Loading)
        assertTrue(result.last() is NetworkResult.Success)
        assertEquals("executeFetchCatBreeds resp Not matching ", verifyData[0].breed,
            (result.last() as NetworkResult.Success).data?.get(0)?.breed
        )

        //Verify
        coVerify(exactly = 1) { repository.fetchCatBreeds() }
        confirmVerified(repository)
    }

    @Test
    fun `executeFetchCatBreeds emits error when repository returns error`() = runTest {
        // Arrange
        val errorMessage = "Error fetching cat breeds"

        val repositoryFlow = flow {
            emit(NetworkResult.Loading()) // First emit Loading
            emit(NetworkResult.Error<CatBreedsResponse>(errorMessage, null)) // Then emit Success
        }
        coEvery { repository.fetchCatBreeds() } returns repositoryFlow

        // Act
        val result = useCase.executeFetchCatBreeds().toList()

        // Assert
        assertTrue(result.first() is NetworkResult.Loading)
        assertTrue(result.last() is NetworkResult.Error)
        assertEquals(errorMessage, (result.last() as NetworkResult.Error).message)
        //Verify
        coVerify(exactly = 1) { repository.fetchCatBreeds() }
        confirmVerified(repository)
    }

    @Test
    fun `executeFetchCatBreeds emits loading when repository emits loading`() = runTest {
        // Arrange
        val repositoryFlow = flowOf(NetworkResult.Loading<CatBreedsResponse>())
        coEvery { repository.fetchCatBreeds() } returns repositoryFlow

        // Act
        val result = useCase.executeFetchCatBreeds().toList()

        // Assert
        assertTrue(result.first() is NetworkResult.Loading)
        assertTrue(result.size == 1) // Only loading is emitted

        //Verify
        coVerify(exactly = 1) { repository.fetchCatBreeds() }
        confirmVerified(repository)
    }
}
