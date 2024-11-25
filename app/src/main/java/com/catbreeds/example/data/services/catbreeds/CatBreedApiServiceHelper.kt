package com.catbreeds.example.data.services.catbreeds

import com.catbreeds.example.data.models.catBreedData.CatBreedsResponse
import retrofit2.Response

interface CatBreedApiServiceHelper {

    suspend fun fetchCatBreeds(limit: Int): Response<CatBreedsResponse>

}