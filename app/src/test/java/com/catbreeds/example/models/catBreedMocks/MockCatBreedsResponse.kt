package com.catbreeds.example.models.catBreedMocks

import com.catbreeds.example.data.models.catBreedData.CatBreed
import com.catbreeds.example.data.models.catBreedData.CatBreedsResponse
import com.catbreeds.example.data.models.catBreedData.PageLink
import com.catbreeds.example.domain.mappers.CatBreedDataModel
import retrofit2.Response

class MockCatBreedsResponse(
    val currentPage: Int = 1,
    val data: List<CatBreed> = listOf( toResponseCatBread(MockCatBreedDataModel())),
    val firstPageUrl: String = "https://catfact.ninja/breeds?page=1",
    val from: Int = 1,
    val lastPageUrl: String = "https://catfact.ninja/breeds?page=10",
    val links: List<PageLink> = emptyList(),
    val nextPageUrl: String? = "https://catfact.ninja/breeds?page=2",
    val path: String = "https://catfact.ninja/breeds",
    val perPage: Int = 10,
    val prevPageUrl: String? = null,
    val to: Int = 10,
    val total: Int = 98
) {
}

fun toCatBreedsAPIResponce(mocksCatBreedResp: MockCatBreedsResponse): Response<CatBreedsResponse> {
    return Response.success(
        CatBreedsResponse(
            currentPage = mocksCatBreedResp.currentPage,
            data = mocksCatBreedResp.data,
            firstPageUrl = mocksCatBreedResp.firstPageUrl,
            from = mocksCatBreedResp.from,
            lastPageUrl = mocksCatBreedResp.lastPageUrl,
            links = mocksCatBreedResp.links,
            nextPageUrl = mocksCatBreedResp.nextPageUrl,
            path = mocksCatBreedResp.path,
            perPage = mocksCatBreedResp.perPage,
            prevPageUrl = mocksCatBreedResp.prevPageUrl,
            to = mocksCatBreedResp.to,
            total = mocksCatBreedResp.total
        )
    )
}

fun toCatBreedDataModels(mocksCatBreedResp: MockCatBreedsResponse): List<CatBreedDataModel> {
    return mocksCatBreedResp.data.map {
        CatBreedDataModel(
            breed = it.breed,
            country = it.country,
            origin = it.origin,
            coat = it.coat,
            pattern = it.pattern
        )
    }
}