/*
package com.catbreeds.example.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.viewmodel.compose.viewModel
import com.catbreeds.example.data.database.UserDatabase
import com.catbreeds.example.data.repositories.CatDetailsRepositoryImpl
import com.catbreeds.example.domain.repositories.CatsRepositoryImpl
import com.catbreeds.example.data.services.cats.CatApiServiceHelper
import com.catbreeds.example.data.services.cats.CatApiServiceHelperImpl
import com.catbreeds.example.data.services.cats.CatsDatabaseHelper
import com.catbreeds.example.data.services.cats.CatsDatabaseHelperImpl
import com.catbreeds.example.data.services.catsDetail.CatDetailsApiServiceHelper
import com.catbreeds.example.data.services.catsDetail.CatDetailsApiServiceHelperImpl
import com.catbreeds.example.data.services.catsDetail.CatsDetailsDatabaseHelper
import com.catbreeds.example.data.services.catsDetail.CatsDetailsDatabaseHelperImpl
import com.catbreeds.example.domain.repositories.CatDetailsRepository
import com.catbreeds.example.domain.repositories.CatsRepository
import com.catbreeds.example.domain.usecase.cats.GetCatsUseCase
import com.catbreeds.example.domain.usecase.cats.GetCatsUseCaseImpl
import com.catbreeds.example.domain.usecase.cats.GetFavCatsUseCase
import com.catbreeds.example.domain.usecase.cats.GetFavCatsUseCaseImpl
import com.catbreeds.example.domain.usecase.catsDetail.CheckFavUseCase
import com.catbreeds.example.domain.usecase.catsDetail.CheckFavouriteUseCaseImpl
import com.catbreeds.example.domain.usecase.catsDetail.DeleteFavCatUseCase
import com.catbreeds.example.domain.usecase.catsDetail.DeleteFavCatUseCaseImpl
import com.catbreeds.example.domain.usecase.catsDetail.PostFavCatUseCase
import com.catbreeds.example.domain.usecase.catsDetail.PostFavCatUseCaseImpl
import com.catbreeds.example.presentation.ui.features.catDetails.viewModel.CatsDetailsViewModel
import com.catbreeds.example.presentation.ui.features.cats.viewModel.CatsViewModel
import com.catbreeds.example.utils.Constants
import com.google.gson.GsonBuilder
import com.pddstudio.preferences.encrypted.EncryptedPreferences
import kotlinx.coroutines.Dispatchers
import net.bytebuddy.matcher.ElementMatchers.named
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val gsonModule = module {
    single { GsonBuilder().create() }
}

private fun getSharedPreferences(androidApplication: Application): SharedPreferences =
    androidApplication.getSharedPreferences(
        Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE
    )

private val persistence = module {
    single<EncryptedPreferences> {
        EncryptedPreferences.Builder(get()).withEncryptionPassword(Constants.PREF_PASSWORD).build()
    }
    single {
        getSharedPreferences(androidApplication())
    }
    single<SharedPreferences.Editor> {
        getSharedPreferences(androidApplication()).edit()
    }
}
private val viewModelModule = module {
    viewModel { CatsViewModel(get(), get()) }
    viewModel { CatsDetailsViewModel(get(), get(), get()) }

}




private val dispatchModule = module {
    single(named("io")) { Dispatchers.IO }
    single(named("main")) { Dispatchers.Main }
    single(named("default")) { Dispatchers.Default }
}





//Add module to allModules for use
val allModules = listOf(
    viewModelModule,
    persistence,
    dispatchModule,
    gsonModule
)*/
