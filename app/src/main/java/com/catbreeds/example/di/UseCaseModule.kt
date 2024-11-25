package com.catbreeds.example.di

import com.catbreeds.example.domain.repositories.CatBreedsRepository
import com.catbreeds.example.domain.usecase.cats.CatBreedsFetchUseCase
import com.catbreeds.example.domain.usecase.cats.CatBreedsFetchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideCatUseCase(catsRepo: CatBreedsRepository): CatBreedsFetchUseCase {
        return CatBreedsFetchUseCaseImpl(catsRepo)
    }

}