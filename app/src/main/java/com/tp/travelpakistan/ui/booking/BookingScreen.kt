package com.tp.travelpakistan.ui.booking

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tp.travelpakistan.formatDate
import com.tp.travelpakistan.showSnackbarErrorMessage
import com.tp.travelpakistan.ui.booking.components.BookingSectionA
import com.tp.travelpakistan.ui.booking.components.BookingSectionB
import com.tp.travelpakistan.ui.booking.components.PassengerType
import com.tp.travelpakistan.ui.booking.components.SelectPickupDialog
import com.tp.travelpakistan.ui.booking.data.models.BookingDataForPayment
import com.tp.travelpakistan.ui.booking.models.cardOptions
import com.tp.travelpakistan.ui.home.ui.components.TravelTopBarV2
import com.tp.travelpakistan.ui.home.ui.model.TourPackage
import com.tp.travelpakistan.ui.home.ui.model.listOfTours

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookingScreen(
    tourPackage: TourPackage?,
    bookingViewModel: BookingViewModel = hiltViewModel(),
    goToPayment: ((BookingDataForPayment) -> Unit)? = null,
    navigateUp: (() -> Unit)? = null
) {


    if (bookingViewModel.tourPackageState.value.tourId == "1") {
        bookingViewModel.initUiState(tourPackage = tourPackage!!)
    }

    val uiState = bookingViewModel.bookingUiState.collectAsState()
    val showDatePickerDialog = remember { mutableStateOf(false) }
    val showPickupLocationDialog = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val showErrorDate = remember {
        mutableStateOf(false)
    }


    @Composable
    fun showErrorMessage(
        message:String
    ){
        showSnackbarErrorMessage(message = message, snackbarHostState = snackbarHostState) {
            showErrorDate.value  = false
        }
    }

    if(showPickupLocationDialog.value){
        SelectPickupDialog(pickupLocations = uiState.value?.pickupLocations, onLocationSelect = {
            bookingViewModel.onPickupSelect(it)
            showPickupLocationDialog.value = false
        }){
            showPickupLocationDialog.value = false
        }
    }

    if (showDatePickerDialog.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
        DatePickerDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                showDatePickerDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePickerDialog.value = false
                        Log.d("Selected date timestamp:", " ${datePickerState.selectedDateMillis?.formatDate()}")
                        datePickerState.selectedDateMillis?.let {
                            bookingViewModel.onDateSelect(it.formatDate())
                        }
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePickerDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }

    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TravelTopBarV2(
                "Booking",
                onBackPress = { navigateUp?.invoke() }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    if(uiState.value!!.bookingDate.isEmpty()) {
                        showErrorDate.value = true
                        return@Button
                    }
                    goToPayment?.invoke(BookingDataForPayment(
                        tourPackage = tourPackage!!,
                        summaryUiModel = uiState.value!!.summaryUiModel
                    ))
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp, end = 16.dp, bottom = 16.dp
                    )
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "GO TO PAYMENT", style = TextStyle(fontSize = 18.sp))
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
        ) {
            if(showErrorDate.value) {
                showErrorMessage(message = "Please select date")
            }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    uiState.value?.let {
                        BookingSectionA(
                            bookingViewModel.tourPackageState.value,
                            bookingUiModel = it,
                            onDateSelection = {
                                showDatePickerDialog.value = true
                            },
                            onLocationSelection = {
                                showPickupLocationDialog.value = true
                            }
                        ) { type, numberOfTravellers ->
                            when (type) {
                                PassengerType.CHILD -> {
                                    bookingViewModel.onNumberOfChildTravellersChange(
                                        numberOfTravellers
                                    )
                                }
                                PassengerType.ADULT -> {
                                    bookingViewModel.onNumberOfTravellersChange(numberOfTravellers)
                                }
                            }
                        }
                    }

                    BookingSectionB(
                        cardOptions,
                        onCardSelected = {}
                    )
                }
            }
        }


}

@Preview
@Composable
fun BookingScreenPreview() {
    BookingScreen(listOfTours[0])
}