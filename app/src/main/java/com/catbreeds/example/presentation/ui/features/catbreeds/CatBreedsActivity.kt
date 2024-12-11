package com.catbreeds.example.presentation.ui.features.catbreeds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.catbreeds.example.presentation.ui.features.catbreeds.view.CatFullDetail
import com.catbreeds.example.presentation.ui.features.catbreeds.view.CatScreen
import com.catbreeds.example.presentation.ui.features.catbreeds.viewModel.CatsViewModel
import com.catbreeds.example.presentation.ui.theme.ComposeSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatBreedsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                NavigationSystem()
            }
        }
    }

    @Composable
    fun NavigationSystem() {
        val navController = rememberNavController()
        val viewModel: CatsViewModel = hiltViewModel()

        NavHost(navController = navController, startDestination = "catBreeds") {

            composable("catBreeds") {
                CatScreen(
                    navController,
                    viewModel =viewModel,
                    )
            }
            composable("catBreedDetails") {

               /* val viewModel: CatsViewModel =
                    if (navController.previousBackStackEntry != null) hiltViewModel(
                        navController.previousBackStackEntry!!
                    ) else hiltViewModel()*/
                CatFullDetail(
                    onBackPressed = {
                        onBackPressedDispatcher.onBackPressed()
                    },
                    item = null,
                    viewModel = viewModel
                )
            }
        }
    }
}