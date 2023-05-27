package com.tp.travelpakistan.ui.virtualtourguide.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.travelpakistan.R
import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.auth.data.UserSession
import com.tp.travelpakistan.ui.home.data.toppicks.TopDestinationsRepository
import com.tp.travelpakistan.ui.home.ui.HomeUiState
import com.tp.travelpakistan.ui.home.ui.VirtualTourGuideUiState
import com.tp.travelpakistan.ui.virtualtourguide.data.alltours.AllDestinationsRepository
import com.tp.travelpakistan.ui.virtualtourguide.data.alltours.models.Destination
import com.tp.travelpakistan.ui.virtualtourguide.ui.components.DestinationSlideSectionItem
import com.tp.travelpakistan.ui.virtualtourguide.ui.components.SlideItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VirtualTourGuideViewModel @Inject constructor(
    private val allDestinationsRepository: AllDestinationsRepository,
    private val userSession: UserSession,
) : ViewModel() {

    private val _virtualTourUiState = MutableStateFlow(VirtualTourGuideUiState(isLoadingData = false, isError = false))
    val homeUiState = _virtualTourUiState

    val currentLoggedUser = userSession.userInfo


    fun getSlideSectionData(destination: Destination){
        val activities = destination.activities.map {
            SlideItem(it.name,it.description)
        }
        val culturalCusine = destination.culturalCusine.map {
            SlideItem(it.name,it.description)
        }
        val culturalTraditions = destination.culturalTraditions.map {
            SlideItem(it.name,it.description)
        }
        val hiddenPlaces = destination.hiddenPlaces.map {
            SlideItem(it.name,it.description)
        }
        val viewPoints = destination.viewPoints.map {
            SlideItem(it.name,it.description)
        }

        val sectionDataList = listOf(
            DestinationSlideSectionItem(
                title = "Activities", icon = R.drawable.ic_activities, items = activities
            ),
            DestinationSlideSectionItem(
                title = "Cultural Cusines", icon = R.drawable.ic_cuisine, items = culturalCusine
            ),
            DestinationSlideSectionItem(
                title = "Cultural Tradition", icon = R.drawable.ic_traditions, items = culturalTraditions
            ),
            DestinationSlideSectionItem(
                title = "Hidden Places", icon = R.drawable.ic_hidden, items = hiddenPlaces
            ),
            DestinationSlideSectionItem(
                title = "View Points", icon = R.drawable.ic_viewpoint, items = viewPoints
            ),
        )

        _virtualTourUiState.update { it.copy(
            slideSections = sectionDataList
        ) }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _virtualTourUiState.update {
                it.copy(isLoadingData = true)
            }
            val response = allDestinationsRepository.getAllDestinations()
            when (response) {
                is Resource.Error -> {
                    _virtualTourUiState.update {
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
                    Log.d(
                        "jeje",
                        "Message: ${response.data?.message}\n Data: ${response.data?.destinations}"
                    )
                    val destinations = response.data?.destinations ?: listOf()




                    _virtualTourUiState.update {
                        it.copy(
                            isLoadingData = false,
                            isError = false,
                            data = destinations
                        )
                    }
                }
            }
        }
    }
}