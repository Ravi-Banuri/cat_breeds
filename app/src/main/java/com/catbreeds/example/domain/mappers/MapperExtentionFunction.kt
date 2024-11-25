package com.catbreeds.example.domain.mappers

import com.catbreeds.example.data.models.SuccessResponse
import com.catbreeds.example.data.models.catBreedData.CatBreed

fun SuccessResponse.mapSuccessData(): CallSuccessModel {
    return CallSuccessModel(
        successMessage = this.message,
        id = this.id
    )
}

fun CatBreed.mapCatBreedsDataItems(): CatBreedDataModel {
    return CatBreedDataModel(
        breed = this.breed,
        country = this.country,
        origin = this.origin,
        coat = this.coat,
        pattern = this.pattern
    )
}



