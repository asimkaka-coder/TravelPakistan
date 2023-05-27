package com.tp.travelpakistan.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.auth.data.UserSession
import com.tp.travelpakistan.ui.home.data.toppicks.TopDestinationsRepository
import com.tp.travelpakistan.ui.home.ui.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val topDestinationsRepository: TopDestinationsRepository,
    private val userSession: UserSession,
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState(isLoadingData = false, isError = false))
    val homeUiState = _homeUiState

    val currentLoggedUser = userSession.userInfo

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _homeUiState.update {
                it.copy(isLoadingData = true)
            }
            val response = topDestinationsRepository.getTopDestinations()
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
                    Log.d(
                        "jeje",
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
}