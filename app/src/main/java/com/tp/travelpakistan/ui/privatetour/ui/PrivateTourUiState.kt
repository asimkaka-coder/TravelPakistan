package com.tp.travelpakistan.ui.privatetour.ui

import com.tp.travelpakistan.PrivateTourInputErrorType
import com.tp.travelpakistan.ui.home.data.toppicks.model.TopToursResponse
import com.tp.travelpakistan.ui.home.data.toppicks.model.Tour
import com.tp.travelpakistan.ui.home.ui.model.TourPackage
import com.tp.travelpakistan.ui.privatetour.data.model.PrivateTourRequestResponse

data class PrivateTourUiState(
    val isLoadingData:Boolean,
    val isError: Boolean,
    val data:PrivateTourRequestResponse?=null,
    val inputErrorType: PrivateTourInputErrorType = PrivateTourInputErrorType.NONE
)
