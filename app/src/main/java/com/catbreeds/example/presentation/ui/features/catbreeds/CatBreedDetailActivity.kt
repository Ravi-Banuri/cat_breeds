package com.catbreeds.example.presentation.ui.features.catbreeds

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.catbreeds.example.presentation.ui.features.catbreeds.view.CatFullDetail
import com.catbreeds.example.presentation.ui.features.catbreeds.viewModel.CatsViewModel
import com.catbreeds.example.presentation.ui.theme.ComposeSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatBreedDetailActivity : ComponentActivity() {
    private val catsViewModel: CatsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                CatsFullView()
            }
        }
    }

    @Composable
    private fun CatsFullView() {
        val selectedBreed =  catsViewModel.selctedCatBreed.value
        Log.d("CatFillImageActivity", "selcted breed :: ${selectedBreed?.breed}")
        CatFullDetail(
            onBackPressed = {
                onBackPressedDispatcher.onBackPressed()
            },
            item = selectedBreed
        )

    }

}
