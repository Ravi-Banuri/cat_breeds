package com.catbreeds.example.domain.usecase.cats

import com.catbreeds.example.data.NetworkResult
import com.catbreeds.example.domain.mappers.CatBreedDataModel
import com.catbreeds.example.domain.mappers.mapCatBreedsDataItems
import com.catbreeds.example.domain.repositories.CatBreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatBreedsFetchUseCaseImpl(private val catsRepo: CatBreedsRepository) : CatBreedsFetchUseCase {


    override suspend fun executeFetchCatBreeds(): Flow<NetworkResult<List<CatBreedDataModel>>> = flow {
        catsRepo.fetchCatBreeds().collect { catBreedsResponse ->
            when (catBreedsResponse) {
                is NetworkResult.Success -> {
                    var catBreedDataList = emptyList<CatBreedDataModel>()
                    catBreedsResponse.data?.let { cat ->
                        catBreedDataList = cat.data.map {  it.mapCatBreedsDataItems()}
                    }
                    emit(NetworkResult.Success(catBreedDataList))
                }

                is NetworkResult.Error -> {
                    emit(NetworkResult.Error(catBreedsResponse.message))
                }

                is NetworkResult.Loading -> {
                    emit(NetworkResult.Loading())
                }
            }
        }
    }
}

