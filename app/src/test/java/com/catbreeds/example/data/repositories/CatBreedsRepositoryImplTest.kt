package com.catbreeds.example.data.repositories

import com.catbreeds.example.data.NetworkResult
import com.catbreeds.example.data.services.CatBreedService
import com.catbreeds.example.data.services.catbreeds.CatBreedApiServiceHelperImpl
import com.catbreeds.example.models.catBreedMocks.MockCatBreedsResponse
import com.catbreeds.example.models.catBreedMocks.toCatBreedsAPIResponce
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class CatBreedsRepositoryImplTest {

    private lateinit var repository: CatBreedsRepositoryImpl
    private val service: CatBreedService = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        val apiHelper = CatBreedApiServiceHelperImpl(service)
        repository = CatBreedsRepositoryImpl(apiHelper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testFetchCatBreeds() = runTest {
        // Arrange
        val mockCatBreedsResponse = MockCatBreedsResponse() // Mock data
        val response = toCatBreedsAPIResponce(mockCatBreedsResponse) // Mock successful API response

        coEvery { service.fetchCatBreeds(10) } returns response

        // Act
        val result = repository.fetchCatBreeds(10).toList()

        // Assert
        assertTrue(result.first() is NetworkResult.Loading)
        assertTrue(result.last() is NetworkResult.Success)
        assertEquals("Repo resp Not matching ", response.body()?.data?.get(0)?.breed, (result.last() as NetworkResult.Success).data?.data?.get(0)?.breed)

        //Verify
        coVerify(exactly = 1) { service.fetchCatBreeds(10) }
        confirmVerified(service)
    }

    @Test
    fun `fetchCats() returns error when API call fails`() = runTest {
        // Arrange
        val errorBody = mockk<ResponseBody>(relaxed = true)
        coEvery { service.fetchCatBreeds(10) } returns Response.error(400, errorBody)

        // Act
        val result = repository.fetchCatBreeds(10)

        // Assert
        assertTrue(result.first() is NetworkResult.Loading)
        assertTrue(result.last() is NetworkResult.Error)

        //Verify
        coVerify(exactly = 1) { service.fetchCatBreeds(10) }
        confirmVerified(service)
    }

    @Test
    fun `fetchCats() handles exceptions gracefully`() = runTest {
        // Arrange
        val exception = RuntimeException("Unexpected error")
        coEvery { service.fetchCatBreeds(10) } throws exception

        // Act
        val result = repository.fetchCatBreeds(10).toList()

        // Assert
        assertTrue(result.first() is NetworkResult.Loading)
        assertTrue(result.last() is NetworkResult.Error)
        assertEquals("Unexpected error", (result.last() as NetworkResult.Error).message)

        //Verify
        coVerify(exactly = 1) { service.fetchCatBreeds(10) }
        confirmVerified(service)
    }
}

