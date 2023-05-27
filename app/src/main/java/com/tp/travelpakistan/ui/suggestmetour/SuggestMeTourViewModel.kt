package com.tp.travelpakistan.ui.suggestmetour

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.travelpakistan.SuggestInputErrorType
import com.tp.travelpakistan.isValidSuggestTourRequest
import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.home.ui.SuggestMeTourUiState
import com.tp.travelpakistan.ui.suggestmetour.data.SuggestMeTourRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuggestMeTourViewModel @Inject constructor(
    private val suggestMeTourRepository: SuggestMeTourRepository,
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(SuggestMeTourUiState(isLoadingData = false, isError = false))
    val homeUiState = _homeUiState


    fun suggestMeTour(
        destination: String,
        days: String,
        budget: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val errorType = isValidSuggestTourRequest(destination,days,budget)){
                SuggestInputErrorType.NONE -> {
                    _homeUiState.update {
                        it.copy(isLoadingData = true)
                    }
                    val response = suggestMeTourRepository.suggestMeTour(
                        destination, days.toInt(), budget.toInt()
                    )
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
                                    data = tourData,
                                    inputErrorType = SuggestInputErrorType.NONE
                                )
                            }
                        }
                    }
                }
                else -> {
                    _homeUiState.update {uiState->
                        uiState.copy(
                            inputErrorType = errorType
                        )
                    }
                }
            }

        }
    }

    fun errorMessageShown(){
        _homeUiState.update {
            it.copy(
                isError = false,
                inputErrorType = SuggestInputErrorType.NONE,
                isLoadingData = false,
            )
        }
    }
}