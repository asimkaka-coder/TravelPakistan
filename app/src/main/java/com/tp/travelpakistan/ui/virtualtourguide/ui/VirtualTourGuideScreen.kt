package com.tp.travelpakistan.ui.virtualtourguide.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.tp.travelpakistan.ui.home.ui.components.TravelTopBarV2
import com.tp.travelpakistan.ui.virtualtourguide.data.alltours.models.Destination

@Composable
fun VirtualTourGuideScreen(
    virtualTourGuideViewModel: VirtualTourGuideViewModel = hiltViewModel(),
    navigateUp: (() -> Unit)? = null,
    goToDetails:(Destination)->Unit={}
    ) {

    val snackbarHostState = remember { SnackbarHostState() }

    val uiState = virtualTourGuideViewModel.homeUiState.collectAsState()




    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TravelTopBarV2(
                "Virtual Tour Guide",
                onBackPress = { navigateUp?.invoke() }
            )
        },
        bottomBar = {
        }
    ) {

        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
        ) {

            if (uiState.value.isLoadingData) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .zIndex(5f)
                    )
                }
            }

            if (uiState.value.isError) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .zIndex(5f), text = "No Destinations Found"
                    )
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(uiState.value.data) {destination->
                        DestinationItem(destination = destination){destination ->
                            goToDetails(destination)
                        }
                    }
                }
            }
        }
    }
}