package com.catbreeds.example.presentation.ui.features.catbreeds.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catbreeds.example.data.NetworkResult
import com.catbreeds.example.domain.mappers.CatBreedDataModel
import com.catbreeds.example.domain.usecase.cats.CatBreedsFetchUseCase
import com.catbreeds.example.presentation.contracts.BaseContract
import com.catbreeds.example.presentation.contracts.CatBreedsContract
import com.catbreeds.example.utils.ErrorsMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(
    private val catUseCase: CatBreedsFetchUseCase
) : ViewModel() {
    init {
        getCatsBreedsData()
    }

    private val _breedsState = MutableStateFlow(
        CatBreedsContract.State(
            catBreeds = listOf(),
            isLoading = true
        )
    )

    val breedsState: StateFlow<CatBreedsContract.State> = _breedsState

    private val _selectedBreed = MutableLiveData<CatBreedDataModel>()
    val selectedBreed: LiveData<CatBreedDataModel> get() = _selectedBreed

    var effects = Channel<BaseContract.Effect>(Channel.UNLIMITED)
        private set

    private fun updateCatBreedState(newState: CatBreedsContract.State) {
        _breedsState.value = newState
    }

    fun updateSelctedCatBreed(catBreed: CatBreedDataModel) {
        _selectedBreed.value = catBreed
    }

    fun getCatsBreedsData() {
        viewModelScope.launch(Dispatchers.IO) {
            catUseCase.executeFetchCatBreeds().collect {
                when (it) {
                    is NetworkResult.Success -> {
                        val newState =
                            breedsState.value.copy(catBreeds = it.data!!, isLoading = false)
                        updateCatBreedState(newState)
                        effects.send(BaseContract.Effect.DataWasLoaded)
                    }

                    is NetworkResult.Error -> {
                        val newState = breedsState.value.copy(isLoading = false)
                        updateCatBreedState(newState)
                        effects.send(
                            BaseContract.Effect.Error(
                                it.message ?: ErrorsMessage.gotApiCallError
                            )
                        )
                    }

                    is NetworkResult.Loading -> {
                        if (!breedsState.value.isLoading!!) {
                            val newState = breedsState.value.copy(isLoading = true)
                            updateCatBreedState(newState)
                        }
                    }

                }
            }

        }
    }

}