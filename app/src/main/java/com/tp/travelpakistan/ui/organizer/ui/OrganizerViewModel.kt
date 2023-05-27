package com.tp.travelpakistan.ui.organizer.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.travelpakistan.ui.organizer.data.OrganizerRepository
import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.auth.data.UserSession
import com.tp.travelpakistan.ui.home.ui.HomeUiState
import com.tp.travelpakistan.ui.home.ui.OrganizerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrganizerViewModel @Inject constructor(
    private val organizerRepository: OrganizerRepository
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(OrganizerUiState(isLoadingData = false, isError = false))
    val homeUiState = _homeUiState

    fun getOrganizerDetails(
        Id:String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _homeUiState.update {
                it.copy(isLoadingData = true)
            }
            val response = organizerRepository.getOrganizerDetails(organizerId =Id )
            when (response) {
                is Resource.Error -> {
                    _homeUiState.update {
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
                    val reviewData = organizerRepository.fetchReviews(organizerId = Id).data
                    val toursData = organizerRepository.fetchTours(organizerId = Id).data
                    Log.d(
                        "jeje",
                        "Message: ${response.data?.message}\n Data: ${response.data}"
                    )
                    val organizerData = response.data
                    _homeUiState.update {
                        it.copy(
                            isLoadingData = false,
                            isError = false,
                            data = organizerData,
                            reviews = reviewData,
                            tours = toursData
                        )
                    }
                }
            }
        }
    }
}