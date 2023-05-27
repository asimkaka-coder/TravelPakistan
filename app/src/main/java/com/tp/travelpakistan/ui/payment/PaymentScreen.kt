package com.tp.travelpakistan.ui.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.tp.travelpakistan.PaymentInputErrorType
import com.tp.travelpakistan.showSnackbarErrorMessage
import com.tp.travelpakistan.ui.booking.data.models.BookingDataForPayment
import com.tp.travelpakistan.ui.home.ui.components.TravelTopBarV2
import com.tp.travelpakistan.ui.payment.data.models.PurchaseTourRequestBody
import com.tp.travelpakistan.ui.payment.data.models.Travellers

@Composable
fun PaymentScreen(
    paymentViewModel: PurchaseViewModel = hiltViewModel(),
    bookingDataForPayment: BookingDataForPayment? = null,
    onPaymentSuccess:()->Unit ={},
    navigateUp: (() -> Unit)? = null
) {
    val showPurchaseSuccessDialog = remember { mutableStateOf(false) }
    val showPurchaseConfirmDialog = remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    if (paymentViewModel.bookingDataForPaymentState.value == null) {
        bookingDataForPayment?.let {
            paymentViewModel.init(it)
        }
    }

    val uiState = paymentViewModel.privateTourUiState.collectAsState()

    @Composable
    fun showErrorMessage(
        message: String
    ) {
        showSnackbarErrorMessage(message = message, snackbarHostState = snackbarHostState) {
            paymentViewModel.errorMessageShown()
        }
    }

    if (uiState.value.isSuccess) {
        PurchaseSuccessDialog(
            onDismiss = {
                paymentViewModel.errorMessageShown()
                onPaymentSuccess()
            }
        )
    }

    if (showPurchaseConfirmDialog.value) {
        ConfirmPurchaseDialog(
            onDismiss = {
                showPurchaseConfirmDialog.value = false
            }
        ) {
            bookingDataForPayment?.let {
                paymentViewModel.postTourRequest(
                    PurchaseTourRequestBody(
                        amount = it.summaryUiModel.subTotal.toInt(),
                        pickup = it.summaryUiModel.pickup,
                        purchasedBy = paymentViewModel.loggedInUser._id,
                        tour = it.tourPackage.tourId,
                        travellers = Travellers(
                            adults = it.summaryUiModel.adultPackages,
                            children = it.summaryUiModel.childPackage,
                            infants = 0
                        )
                    ),
                    paymentFormState = paymentViewModel.paymentFormState.value
                )
            }
            showPurchaseConfirmDialog.value = false
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TravelTopBarV2(
                "Payment",
                onBackPress = { navigateUp?.invoke() }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    showPurchaseConfirmDialog.value = true
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
                Text(text = "PAY", style = TextStyle(fontSize = 18.sp))
            }
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
                showErrorMessage(message = "Something Went Wrong, try again")
            }

            when (uiState.value.inputErrorType) {
                PaymentInputErrorType.CVV -> {
                    showErrorMessage(message = "Enter correct CVV")
                }
                PaymentInputErrorType.CardHolderName -> {
                    showErrorMessage(message = "Enter valid Card Holder Name")

                }
                PaymentInputErrorType.CardNumber -> {
                    showErrorMessage(message = "Enter valid card number")

                }
                PaymentInputErrorType.Country -> {
                    showErrorMessage(message = "Enter valid country")

                }
                PaymentInputErrorType.ExpiryDate -> {
                    showErrorMessage(message = "Enter valid date")

                }
                PaymentInputErrorType.NONE -> {

                }
                PaymentInputErrorType.PostalCode -> {
                    showErrorMessage(message = "Enter valid postal code")

                }
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                PaymentForm(
                    paymentFormState = paymentViewModel.paymentFormState.value
                ) { value, type ->
                    paymentViewModel.onFormStateChange(value, type)
                }
                paymentViewModel.bookingDataForPaymentState.value?.let { bookingDataForPayment ->
                    PaymentInfoSection(
                        bookingDataForPayment
                    )
                }


            }
        }
    }

}

@Preview
@Composable
fun PaymentScreenPreview() {
    PaymentScreen()
}