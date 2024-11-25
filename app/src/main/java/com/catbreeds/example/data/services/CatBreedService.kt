package com.catbreeds.example.data.services


import com.catbreeds.example.data.models.catBreedData.CatBreedsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatBreedService {

    //cat breeds
    @GET("breeds")
    suspend fun fetchCatBreeds(
        @Query("limit") limit: Int
    ): Response<CatBreedsResponse>
}