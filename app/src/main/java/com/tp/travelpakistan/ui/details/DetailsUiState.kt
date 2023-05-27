package com.tp.travelpakistan.ui.details

import com.tp.travelpakistan.ui.home.data.toppicks.model.TopToursResponse
import com.tp.travelpakistan.ui.home.data.toppicks.model.Tour
import com.tp.travelpakistan.ui.home.ui.model.TourPackage

data class DetailsUiState(
    val isLoadingData:Boolean,
    val isError: Boolean,
    val data:Tour?=null
)
