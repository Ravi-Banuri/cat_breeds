package com.catbreeds.example.domain.repositories


import com.catbreeds.example.data.NetworkResult
import com.catbreeds.example.data.models.catBreedData.CatBreedsResponse
import kotlinx.coroutines.flow.Flow

interface CatBreedsRepository {

    suspend fun fetchCatBreeds(limit: Int = 10): Flow<NetworkResult<CatBreedsResponse>>

}