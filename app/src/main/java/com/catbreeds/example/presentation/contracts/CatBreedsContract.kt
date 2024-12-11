package com.catbreeds.example.presentation.contracts

import com.catbreeds.example.domain.mappers.CatBreedDataModel

class CatBreedsContract {
    data class State(
        val catBreeds: List<CatBreedDataModel> = listOf(),
        val isLoading: Boolean = false
    )
}