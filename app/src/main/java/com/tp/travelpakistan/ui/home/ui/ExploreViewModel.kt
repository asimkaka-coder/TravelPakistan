package com.tp.travelpakistan.ui.home.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.ThrottleLatest
import com.tp.travelpakistan.ui.home.data.explore.ExploreDestinationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val exploreDestinationsRepository: ExploreDestinationsRepository,
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState(isLoadingData = false, isError = false))
    val homeUiState = _homeUiState

    private val throttleLatest = ThrottleLatest()

    init {
        searchTours("a", "a", 1, 1)
    }

    fun searchTours(
        destination: String,
        pickup: String,
        people: Int,
        days: Int
    ) = throttleLatest.debounce(viewModelScope){
        _homeUiState.update {
            it.copy(isLoadingData = true)
        }
        val response = exploreDestinationsRepository.searchTours(
            destination, pickup, people, days
        )
        when (response) {
            is Resource.Error -> {
                _homeUiState.update {
                    it.copy(
                        isLoadingData = false,
                        isError = true
                    )
                }
                Log.d("jejeExplore", "Error: ${response.message}")
            }
            is Resource.Loading -> {

            }
            is Resource.Success -> {
                Log.d(
                    "jejeExplore",
                    "Message: ${response.data?.message}\n Data: ${response.data?.tours}"
                )
                val tourData = response.data?.tours?.map {
                    return@map it.toTourPackage()
                } ?: listOf()

                _homeUiState.update {
                    it.copy(
                        isLoadingData = false,
                        isError = false,
                        data = tourData
                    )
                }
            }
        }
    }
}