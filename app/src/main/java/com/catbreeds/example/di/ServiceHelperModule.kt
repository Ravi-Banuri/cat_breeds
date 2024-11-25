package com.catbreeds.example.di

import com.catbreeds.example.data.services.CatBreedService
import com.catbreeds.example.data.services.catbreeds.CatBreedApiServiceHelper
import com.catbreeds.example.data.services.catbreeds.CatBreedApiServiceHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ServiceHelperModule {

    @Provides
    fun provideCatApiServiceHelper(apiService: CatBreedService): CatBreedApiServiceHelper {
        return CatBreedApiServiceHelperImpl(apiService)
    }
}