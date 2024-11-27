package com.catbreeds.example.data.repositories

import com.catbreeds.example.data.NetworkResult
import com.catbreeds.example.data.models.catBreedData.CatBreedsResponse
import com.catbreeds.example.data.services.catbreeds.CatBreedApiServiceHelper
import com.catbreeds.example.domain.repositories.CatBreedsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CatBreedsRepositoryImpl(
    private val catsApiService: CatBreedApiServiceHelper
) : CatBreedsRepository {

    override suspend fun fetchCatBreeds(limit: Int)= flow<NetworkResult<CatBreedsResponse>> {
        emit(NetworkResult.Loading())
        with(catsApiService.fetchCatBreeds(limit)) {
            if (isSuccessful) {
                emit(NetworkResult.Success(this.body()))
            } else {
                emit(NetworkResult.Error(this.errorBody().toString()))
            }
        }
    }.catch {
        emit(NetworkResult.Error(it.localizedMessage))
    }


}