package com.catbreeds.example.presentation.ui.features.catbreeds.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.catbreeds.example.domain.mappers.CatBreedDataModel
import com.catbreeds.example.presentation.contracts.CatBreedsContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedDataRepository @Inject constructor() {

    private val _breedsState = MutableStateFlow(
        CatBreedsContract.State(
            catBreeds = listOf(),
            isLoading = true
        )
    )

    val breedsState: StateFlow<CatBreedsContract.State> = _breedsState

    private val _selectedBreed = MutableLiveData<CatBreedDataModel>()
    val selectedBreed: LiveData<CatBreedDataModel> get() = _selectedBreed

    fun updateSelctedBreed(catBreed: CatBreedDataModel) {
        _selectedBreed.value = catBreed
    }

    fun updateData(newState: CatBreedsContract.State) {
        _breedsState.value = newState
    }
}
