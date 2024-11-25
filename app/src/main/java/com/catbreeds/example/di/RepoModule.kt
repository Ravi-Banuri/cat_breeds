package com.catbreeds.example.di

import com.catbreeds.example.domain.repositories.CatBreedsRepositoryImpl
import com.catbreeds.example.data.services.catbreeds.CatBreedApiServiceHelper
import com.catbreeds.example.domain.repositories.CatBreedsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {
    @Provides
    fun provideCatRepository(
        catsApiService: CatBreedApiServiceHelper,
    ): CatBreedsRepository {
        return CatBreedsRepositoryImpl(catsApiService)
    }
}