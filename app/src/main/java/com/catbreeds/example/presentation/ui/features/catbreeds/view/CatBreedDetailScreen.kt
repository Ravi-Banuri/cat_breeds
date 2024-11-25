package com.catbreeds.example.presentation.ui.features.catbreeds.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catbreeds.example.R
import com.catbreeds.example.domain.mappers.CatBreedDataModel
import com.catbreeds.example.presentation.ui.theme.lightYellow
import com.catbreeds.example.utils.TestTags.BACK_NAVIGATION_ICON_DESCRIPTION
import com.catbreeds.example.utils.TestTags.FULL_SCREEN_APP_BAR

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CatFullDetail(
    onBackPressed: () -> Unit,
    item: CatBreedDataModel?
) {
    Scaffold( // in scaffold we are specifying top bar.
        topBar = { CatFullScreenAppBar(onBackPressed, stringResource(R.string.breed_details)) }) {
        Surface(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            CatDetails(item)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatFullScreenAppBar(onBackPressed: () -> Unit, title: String) {
    // inside top bar we are specifying background color.
    TopAppBar(
        colors = centerAlignedTopAppBarColors(
            containerColor = colorResource(R.color.colorPrimary),
            titleContentColor = Color(R.color.colorPrimary),
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = BACK_NAVIGATION_ICON_DESCRIPTION
                )
            }
        },
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .testTag(title)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }, modifier = Modifier.testTag(FULL_SCREEN_APP_BAR)
    )
}

// Calling this function as content in the above function
@Composable
fun CatDetails(item: CatBreedDataModel?) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(top = 1.dp),
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .align(Alignment.BottomCenter),
        ) {

            if ( !item?.breed.isNullOrBlank()) {
                Column(
                    modifier = Modifier
                        .background(lightYellow)
                        .fillMaxWidth()
                        .align(Alignment.BottomStart),
                ) {
                    Text(
                        text = "${stringResource(R.string.breed)} : ${item?.breed ?: ""}",

                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = colorResource(id = R.color.black),
                    )
                    item?.origin?.let {
                        Text(
                            text = "${stringResource(R.string.origin)} : ${it}",
                            modifier = Modifier.padding(horizontal = 10.dp),
                            color = colorResource(id = R.color.black),
                        )
                    }
                    Text(
                        text = "${stringResource(R.string.coat)} : ${item?.coat ?: ""}",
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = colorResource(id = R.color.black),
                    )
                    Text(
                        text = "${stringResource(R.string.country)} : ${item?.country ?: ""}",
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = colorResource(id = R.color.black),
                    )
                }
            }
        }
    }
}



