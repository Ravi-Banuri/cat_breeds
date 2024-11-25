package com.catbreeds.example.domain.usecase.cats

import com.catbreeds.example.data.NetworkResult
import com.catbreeds.example.domain.mappers.CatBreedDataModel
import kotlinx.coroutines.flow.Flow

interface CatBreedsFetchUseCase {

    suspend fun executeFetchCatBreeds(): Flow<NetworkResult<List<CatBreedDataModel>>>
}