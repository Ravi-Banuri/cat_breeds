package com.catbreeds.example.models.catBreedMocks

import com.catbreeds.example.data.models.catBreedData.CatBreed
import com.catbreeds.example.domain.mappers.CatBreedDataModel

data class MockCatBreedDataModel(
    val breed: String = "Abyssinian",
    val country: String = "Ethiopia",
    val origin: String = "Natural/Standard",
    val coat: String = "Short",
    val pattern: String = "Ticked"
)

fun toResponseCatBread(mockCatBreedData: MockCatBreedDataModel): CatBreed {
    return CatBreed(
        breed = mockCatBreedData.breed,
        country = mockCatBreedData.country,
        origin = mockCatBreedData.origin,
        coat = mockCatBreedData.coat,
        pattern = mockCatBreedData.pattern
    )
}

fun toResponseCats(mockCatBreedData: MockCatBreedDataModel): List<CatBreedDataModel> {
    return listOf(
        CatBreedDataModel(
            breed = mockCatBreedData.breed,
            country = mockCatBreedData.country,
            origin = mockCatBreedData.origin,
            coat = mockCatBreedData.coat,
            pattern = mockCatBreedData.pattern
        )
    )
}

