package com.catbreeds.example.presentation.contracts

import com.catbreeds.example.domain.mappers.CallSuccessModel
import com.catbreeds.example.domain.mappers.CatBreedDataModel
import com.catbreeds.example.domain.mappers.CatDataModel

class CatBreedsContract {
    data class State(
        val catBreeds: List<CatBreedDataModel> = listOf(),
        val isLoading: Boolean = false
    )
}