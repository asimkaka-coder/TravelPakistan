package com.tp.travelpakistan.ui.home.ui

import com.tp.travelpakistan.InputFieldErrorType
import com.tp.travelpakistan.SuggestInputErrorType
import com.tp.travelpakistan.ui.organizer.data.models.OrganizerResponse
import com.tp.travelpakistan.ui.organizer.data.models.ReviewResponse
import com.tp.travelpakistan.ui.home.data.toppicks.model.TopToursResponse
import com.tp.travelpakistan.ui.home.ui.model.TourPackage
import com.tp.travelpakistan.ui.virtualtourguide.data.alltours.models.Destination
import com.tp.travelpakistan.ui.virtualtourguide.ui.components.DestinationSlideSectionItem

data class HomeUiState(
    val isLoadingData:Boolean,
    val isError: Boolean,
    val data:List<TourPackage> = listOf(),
)

data class VirtualTourGuideUiState(
    val isLoadingData:Boolean,
    val isError: Boolean,
    val data:List<Destination> = listOf(),
    val slideSections:List<DestinationSlideSectionItem> = listOf()
)


data class SuggestMeTourUiState(
    val isLoadingData:Boolean,
    val isError: Boolean,
    val data:List<TourPackage> = listOf(),
    val inputErrorType: SuggestInputErrorType = SuggestInputErrorType.NONE
)

data class OrganizerUiState(
    val isLoadingData:Boolean,
    val isError: Boolean,
    val data: OrganizerResponse?=null,
    val reviews: ReviewResponse?=null,
    val tours:TopToursResponse?=null
)