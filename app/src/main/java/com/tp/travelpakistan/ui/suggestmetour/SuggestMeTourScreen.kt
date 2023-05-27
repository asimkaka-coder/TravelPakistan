package com.tp.travelpakistan.ui.suggestmetour

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tp.travelpakistan.InputFieldErrorType
import com.tp.travelpakistan.SuggestInputErrorType
import com.tp.travelpakistan.showSnackbarErrorMessage
import com.tp.travelpakistan.ui.booking.components.BookingSectionA
import com.tp.travelpakistan.ui.booking.components.BookingSectionB
import com.tp.travelpakistan.ui.booking.models.cardOptions
import com.tp.travelpakistan.ui.home.ui.components.TourItem
import com.tp.travelpakistan.ui.home.ui.components.TravelTopBarV2

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SuggestMeTourScreen(
    suggestMeTourViewModel: SuggestMeTourViewModel = hiltViewModel(),
    navigateUp: (() -> Unit)? = null,
    GoToDetails: ((String) -> Unit)? = null
) {


    val uiState = suggestMeTourViewModel.homeUiState.collectAsState()
    val showSuggestedTour = rememberSaveable {
        mutableStateOf(false)
    }
    val snackbarHostState = remember { SnackbarHostState() }


    @Composable
    fun showErrorMessage(
        message: String
    ) {
        showSnackbarErrorMessage(message = message, snackbarHostState = snackbarHostState) {
            suggestMeTourViewModel.errorMessageShown()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TravelTopBarV2(
                "Suggest me a Tour",
                onBackPress = { navigateUp?.invoke() }
            )
        }
    ) {

        if (uiState.value.isError) {
            showErrorMessage(message = "Something went wrong, try again")
        }

        when (uiState.value.inputErrorType) {
            SuggestInputErrorType.Budget -> {
                showErrorMessage(message = "Enter valid budget amount")

            }
            SuggestInputErrorType.Days -> {
                showErrorMessage(message = "Enter valid days")

            }
            SuggestInputErrorType.Destination -> {
                showErrorMessage(message = "Enter valid destination")
            }
            SuggestInputErrorType.NONE -> {

            }
        }

        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SuggestTourForm() { destination, days, budget ->
                    suggestMeTourViewModel.suggestMeTour(
                        destination, days, budget
                    )
                }

                if (uiState.value.isLoadingData) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }

                if (uiState.value.isError) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = "No Tours Found"
                        )
                    }
                }

                uiState.value.data.let {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(it) {
                            SuggestedTourResult(tourPackage = it, GoToDetails = { tourId ->
                                GoToDetails?.invoke(tourId)
                            })
                        }
                    }
                }
            }
        }

    }


}

@Preview
@Composable
fun SuggestMeTourScreenPreview() {
    SuggestMeTourScreen()
}