package com.catbreeds.example.presentation.ui.features.catbreeds.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.catbreeds.example.R
import com.catbreeds.example.domain.mappers.CatBreedDataModel
import com.catbreeds.example.presentation.contracts.BaseContract
import com.catbreeds.example.presentation.contracts.CatBreedsContract
import com.catbreeds.example.presentation.ui.components.EmptyView
import com.catbreeds.example.presentation.ui.features.catbreeds.viewModel.CatsViewModel
import com.catbreeds.example.presentation.ui.theme.lightYellow
import com.catbreeds.example.utils.TestTags
import com.catbreeds.example.utils.TestTags.PROGRESS_BAR
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CatScreen(
    navController: NavController,
    viewModel: CatsViewModel
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val catMessage = stringResource(R.string.fetch_success)


    // Listen for side effects from the VM
    LaunchedEffect(Unit) {
        viewModel.effects?.receiveAsFlow()?.onEach { effect ->
            if (effect is BaseContract.Effect.DataWasLoaded)
                snackBarHostState.showSnackbar(
                    message = catMessage,
                    duration = SnackbarDuration.Short
                )
        }?.collect { value ->
            if (value is BaseContract.Effect.Error) {
                // Handle other emitted values if needed
                Toast.makeText(context, value.errorMessage, Toast.LENGTH_LONG).show()
            }

        }
    }
    Scaffold(
        topBar = {
            CatAppBar()
        },
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(top = 5.dp)) {
            CatBreedsListScreen(
                navController,
                viewModel.breedsState.collectAsState().value,
                onNavigationRequested = { item->
                    viewModel.updateSelctedCatBreed(item)

                }
            )
        }
    }
}

@Composable
fun CatBreedsListScreen(
    navController: NavController,
    state: CatBreedsContract.State,
    onNavigationRequested: (item: CatBreedDataModel) -> Unit
) {
    Surface() {
        Box {
            val cats = state.catBreeds
            if (!state.isLoading && cats.isEmpty()) {
                EmptyView(message = stringResource(R.string.no_cat_breeds_founds))
            } else
                CatsList(
                    navController,
                    cats = cats,
                    isLoading = state.isLoading
                ) { item ->
                    onNavigationRequested(item)
                }
            if (state.isLoading)
                LoadingBar()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CatAppBar() {
    TopAppBar(
        modifier = Modifier.semantics { testTag = TestTags.CAT_SCREEN_APP_BAR },

        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = colorResource(id = R.color.white),
            )
        },
        colors = centerAlignedTopAppBarColors(
            containerColor = colorResource(R.color.colorPrimary),
            titleContentColor = Color(R.color.white),
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        )
    )
}

@Composable
fun CatsList(
    navController: NavController,
    isLoading: Boolean = false,
    cats: List<CatBreedDataModel>,
    onItemClicked: (item: CatBreedDataModel) -> Unit = { _: CatBreedDataModel -> },

    ) {
    LazyColumn(Modifier.fillMaxSize()) {
        items(cats.size) { index ->
            val item = cats[index]
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .padding(start = 5.dp, end = 5.dp, top = 10.dp)
                    .clickable {
                        if (!isLoading) {
                            onItemClicked(item)
                            navController.navigate("catBreedDetails")
                        }
                    },
            ) {

                if (!item.breed.isNullOrBlank()) {
                    Column(
                        modifier = Modifier
                            .background(lightYellow)
                            .fillMaxWidth()
                            .align(Alignment.BottomStart),
                    ) {
                        Text(
                            text = item.breed,

                            modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 10.dp, bottom = 10.dp),
                            color = colorResource(id = R.color.black),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingBar() {
    Box(
        modifier = Modifier
            .semantics { testTag = TestTags.LOADING_BAR_TAG }
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center

    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .testTag(PROGRESS_BAR)
                .size(60.dp),
            color = colorResource(id = R.color.colorPrimary),
            strokeWidth = 5.dp,
            trackColor = lightYellow,
            strokeCap = StrokeCap.Round
        )
    }
}

