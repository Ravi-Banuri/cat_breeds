package com.catbreeds.example.data.services.catbreeds

import com.catbreeds.example.data.models.catBreedData.CatBreedsResponse
import com.catbreeds.example.data.services.CatBreedService
import retrofit2.Response

class CatBreedApiServiceHelperImpl(private val service: CatBreedService) : CatBreedApiServiceHelper {

    //Cat breeds
    override suspend fun fetchCatBreeds(limit: Int): Response<CatBreedsResponse> =
        service.fetchCatBreeds(limit)
}