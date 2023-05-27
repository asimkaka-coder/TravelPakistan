package com.tp.travelpakistan.ui.privatetour.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.tp.travelpakistan.PrivateTourInputErrorType
import com.tp.travelpakistan.showSnackbarErrorMessage
import com.tp.travelpakistan.ui.home.ui.components.TravelTopBarV2
import com.tp.travelpakistan.ui.payment.PurchaseSuccessDialog
import com.tp.travelpakistan.ui.privatetour.PrivateTourViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrivateTourScreen(
    privateTourViewModel: PrivateTourViewModel = hiltViewModel(),
    navigateUp: (() -> Unit)? = null
) {

    val context = LocalContext.current

    val paymentFormState = remember {
        mutableStateOf(
            PrivateTourFormState(
                "", "", "", "", "", "", "", "", ""
            )
        )
    }
    val snackbarHostState = remember { SnackbarHostState() }

    val showSuccessDialog = remember {
        mutableStateOf(false)
    }


    val uiState = privateTourViewModel.privateTourUiState.collectAsState()

    @Composable
    fun showErrorMessage(
        message:String
    ){
        showSnackbarErrorMessage(message = message, snackbarHostState = snackbarHostState) {
            privateTourViewModel.errorMessageShown()
        }
    }

    if(showSuccessDialog.value){
        PurchaseSuccessDialog(
            message = "Private Tour Request Created Successfully!"
        ){
            showSuccessDialog.value = false
            navigateUp?.invoke()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TravelTopBarV2(
                "Request a Private Tour",
                onBackPress = { navigateUp?.invoke() }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    privateTourViewModel.postTourRequest(paymentFormState.value)
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                    .height(54.dp)
                    ,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "Request",
                    style = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onPrimary)
                )
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
        ) {

            if(uiState.value.isLoadingData){
                Box(modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator(Modifier.align(Alignment.Center).zIndex(5f))
                }
            }

            if(uiState.value.data != null){
                showSuccessDialog.value = true
            }

            if (uiState.value.isError) {
                showErrorMessage(message = "Something Went Wrong, try again")
            }

            when (uiState.value.inputErrorType) {
                PrivateTourInputErrorType.Destination -> {
                    showErrorMessage(message = "Enter valid destination")
                }
                PrivateTourInputErrorType.AdditionalRequirements -> {
                    showErrorMessage(message = "Enter valid additional information")
                }
                PrivateTourInputErrorType.Budget -> {
                    showErrorMessage(message = "Enter valid budget amount")
                }
                PrivateTourInputErrorType.DepartureDate -> {
                    showErrorMessage(message = "Enter valid departure date")
                }
                PrivateTourInputErrorType.DepartureTime -> {
                    showErrorMessage(message = "Enter valid departure time")
                }
                PrivateTourInputErrorType.EstimatedDays -> {
                    showErrorMessage(message = "Enter valid estimated days")
                }
                PrivateTourInputErrorType.NONE -> {

                }
                PrivateTourInputErrorType.NumberOfTravellers -> {
                    showErrorMessage(message = "Enter valid number of travellers")
                }
                PrivateTourInputErrorType.PickupLocation -> {
                    showErrorMessage(message = "Enter valid pickup")
                }
                PrivateTourInputErrorType.Route -> {
                    showErrorMessage(message = "Enter valid route")
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {


                PrivateTourForm(paymentFormState.value) {
                    when (it) {
                        is PrivateTourFormValueChange.AdditionalRequirements -> {
                            paymentFormState.value = paymentFormState.value.copy(
                                additionalRequirements = it.newValue
                            )
                        }
                        is PrivateTourFormValueChange.Budget -> {
                            paymentFormState.value = paymentFormState.value.copy(
                                budget = it.newValue
                            )
                        }
                        is PrivateTourFormValueChange.DepartureDate -> {
                            paymentFormState.value = paymentFormState.value.copy(
                                departureDate = it.newValue
                            )
                        }
                        is PrivateTourFormValueChange.DepartureTime -> {
                            paymentFormState.value = paymentFormState.value.copy(
                                departureTime = it.newValue
                            )
                        }
                        is PrivateTourFormValueChange.Destination -> {
                            paymentFormState.value = paymentFormState.value.copy(
                                destination = it.newValue
                            )
                        }
                        is PrivateTourFormValueChange.EstimatedDays -> {
                            paymentFormState.value = paymentFormState.value.copy(
                                estimatedDays = it.newValue
                            )
                        }
                        is PrivateTourFormValueChange.NumberOfTravellers -> {
                            paymentFormState.value = paymentFormState.value.copy(
                                numberOgTravellers = it.newValue
                            )
                        }
                        is PrivateTourFormValueChange.PickupLocation -> {
                            paymentFormState.value = paymentFormState.value.copy(
                                pickupLocation = it.newValue
                            )
                        }
                        is PrivateTourFormValueChange.Route -> {
                            paymentFormState.value = paymentFormState.value.copy(
                                route = it.newValue
                            )
                        }
                    }
                    Log.d("jeje", "From Tour Screen ${it}")
                }
                PrivateTourStepsSection()
                PrivateTourInfoSection()
            }
        }

    }


}

@Preview
@Composable
fun PrivateTourScreenPreview() {
    PrivateTourScreen()
}