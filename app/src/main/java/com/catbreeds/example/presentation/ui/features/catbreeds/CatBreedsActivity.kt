package com.catbreeds.example.presentation.ui.features.catbreeds

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.catbreeds.example.presentation.ui.features.catbreeds.view.CatScreen
import com.catbreeds.example.presentation.ui.features.catbreeds.viewModel.CatsViewModel
import com.catbreeds.example.presentation.ui.theme.ComposeSampleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.receiveAsFlow

@AndroidEntryPoint
class CatBreedsActivity : ComponentActivity() {
    private val viewModel: CatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                CatsDestination()
            }
        }
    }


    @Composable
    fun CatsDestination() {
        CatScreen(
            state = viewModel.breedsState.collectAsState().value,
            effectFlow = viewModel.effects.receiveAsFlow(),
            onNavigationRequested = { item ->
                viewModel.updateSelctedCatBreed(item)
                startActivity(Intent(
                    this@CatBreedsActivity,
                    CatBreedDetailActivity::class.java
                ))
            },
           )
    }


}