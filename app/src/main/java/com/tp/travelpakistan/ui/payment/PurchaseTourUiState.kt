package com.tp.travelpakistan.ui.payment

import com.tp.travelpakistan.PaymentInputErrorType
import com.tp.travelpakistan.PrivateTourInputErrorType
import com.tp.travelpakistan.ui.booking.data.models.BookingDataForPayment
import com.tp.travelpakistan.ui.home.data.toppicks.model.TopToursResponse
import com.tp.travelpakistan.ui.home.data.toppicks.model.Tour
import com.tp.travelpakistan.ui.home.ui.model.TourPackage
import com.tp.travelpakistan.ui.payment.data.models.PurchaseTourResponse
import com.tp.travelpakistan.ui.privatetour.data.model.PrivateTourRequestResponse

data class PurchaseTourUiState(
    val isLoadingData:Boolean,
    val isError: Boolean,
    val isSuccess:Boolean,
    val data:PurchaseTourResponse?=null,
    val inputErrorType: PaymentInputErrorType = PaymentInputErrorType.NONE
)
