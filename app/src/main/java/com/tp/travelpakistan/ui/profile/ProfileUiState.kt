package com.tp.travelpakistan.ui.profile

import com.tp.travelpakistan.ui.home.ui.model.TourPackage
import com.tp.travelpakistan.ui.payment.data.models.PurchasedTourTicket
import com.tp.travelpakistan.ui.payment.ui.components.PurchasedTicket
import com.tp.travelpakistan.ui.profile.privatetour.PrivateTour

data class ProfileUiState(
    val isLoadingData:Boolean,
    val isError: Boolean,
    val data:List<PurchasedTicket>,
    val requestsData:List<PrivateTour> = listOf()
)
