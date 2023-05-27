package com.tp.travelpakistan.ui.payment

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.travelpakistan.PaymentInputErrorType
import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.auth.data.UserSession
import com.tp.travelpakistan.ui.booking.data.models.BookingDataForPayment
import com.tp.travelpakistan.ui.payment.data.PurchaseTourRepository
import com.tp.travelpakistan.ui.payment.data.models.PurchaseTourRequestBody
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(
    private val purchaseTourRepository: PurchaseTourRepository,
    private val userSession: UserSession
) : ViewModel() {

    private val _privateTourUiState =
        MutableStateFlow(PurchaseTourUiState(isLoadingData = false, isError = false, isSuccess = false))
    val privateTourUiState = _privateTourUiState


    val loggedInUser = userSession.userInfo!!
    val paymentFormState = mutableStateOf(
        PaymentFormState(
            cardHolderName = "",
            cardNumber = "",
            expirationDate = "",
            CVV = "",
            country = "Pakistan",
            postalCode = ""
        )
    )

    val bookingDataForPaymentState:MutableState<BookingDataForPayment?> = mutableStateOf(null)

    fun init(bookingDataForPayment: BookingDataForPayment) {
        bookingDataForPaymentState.value = bookingDataForPayment
    }

    fun onFormStateChange(value: String, type: PaymentInputType) {
        when (type) {
            PaymentInputType.HOLDER_NAME -> {
                paymentFormState.value = paymentFormState.value.copy(
                    cardHolderName = value
                )
            }
            PaymentInputType.CARD_NUMBER -> {
                paymentFormState.value = paymentFormState.value.copy(
                    cardNumber = value
                )
            }
            PaymentInputType.EXPIRATION_DATE -> {
                paymentFormState.value = paymentFormState.value.copy(
                    expirationDate = value
                )
            }
            PaymentInputType.CVV -> {
                paymentFormState.value = paymentFormState.value.copy(
                    CVV = value
                )
            }
            PaymentInputType.COUNTRY -> {
                paymentFormState.value = paymentFormState.value.copy(
                    country = value
                )
            }
            PaymentInputType.POSTSAL_CODE -> {
                paymentFormState.value = paymentFormState.value.copy(
                    postalCode = value
                )
            }
        }
    }

    fun postTourRequest(
        purchaseTourRequestBody: PurchaseTourRequestBody,
        paymentFormState: PaymentFormState
    ) {

        viewModelScope.launch(Dispatchers.IO) {

            when (val inputError = paymentFormState.isValid()) {

                PaymentInputErrorType.NONE -> {
                    _privateTourUiState.update {
                        it.copy(isLoadingData = true)
                    }
                    Log.d("jejeBody", "Error: ${purchaseTourRequestBody}")

                    delay(3000L)
                    val response =
                        purchaseTourRepository.purchaseTour(purchaseTourRequestBody)
                    when (response) {
                        is Resource.Error -> {
                            _privateTourUiState.update {
                                it.copy(
                                    isLoadingData = false,
                                    isError = true
                                )
                            }
                            Log.d("jeje", "Error: ${response.message}")
                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {

                            _privateTourUiState.update {
                                it.copy(
                                    isLoadingData = false,
                                    isError = false,
                                    isSuccess = true,
                                    data = response.data,
                                    inputErrorType = PaymentInputErrorType.NONE
                                )
                            }
                        }
                    }
                }
                else -> {
                    _privateTourUiState.update {
                        it.copy(
                            inputErrorType = inputError
                        )
                    }
                }
            }

        }
    }

    fun errorMessageShown() {
        _privateTourUiState.update {
            it.copy(
                isError = false,
                isLoadingData = false,
                isSuccess = false,
                inputErrorType = PaymentInputErrorType.NONE
            )
        }
    }
}