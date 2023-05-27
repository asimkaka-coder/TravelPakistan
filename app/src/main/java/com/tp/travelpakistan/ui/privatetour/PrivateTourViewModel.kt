package com.tp.travelpakistan.ui.privatetour

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.travelpakistan.PrivateTourInputErrorType
import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.auth.data.UserSession
import com.tp.travelpakistan.ui.privatetour.data.PrivateTourRequestRepo
import com.tp.travelpakistan.ui.privatetour.ui.PrivateTourFormState
import com.tp.travelpakistan.ui.privatetour.ui.PrivateTourUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrivateTourViewModel @Inject constructor(
    private val privateTourRequestRepo: PrivateTourRequestRepo,
    private val userSession: UserSession
) : ViewModel() {

    private val _privateTourUiState =
        MutableStateFlow(PrivateTourUiState(isLoadingData = false, isError = false))
    val privateTourUiState = _privateTourUiState

    fun postTourRequest(privateTourFormState: PrivateTourFormState) {

        viewModelScope.launch(Dispatchers.IO) {

            when (val inputError = privateTourFormState.isValid()) {

                PrivateTourInputErrorType.NONE -> {
                    _privateTourUiState.update {
                        it.copy(isLoadingData = true)
                    }
                    val requestBody =
                        privateTourFormState.toPrivateTourRequestBody(userId = userSession.userInfo!!._id)
                    Log.d("jejeBody", "Error: ${requestBody}")


                    val response =
                        privateTourRequestRepo.postPrivateTourRequest(privateTourRequestBody = requestBody)
                    when (response) {
                        is Resource.Error -> {
                            _privateTourUiState.update {
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

                            _privateTourUiState.update {
                                it.copy(
                                    isLoadingData = false,
                                    isError = false,
                                    data = response.data,
                                    inputErrorType = PrivateTourInputErrorType.NONE
                                )
                            }
                        }
                    }
                }
                else -> {
                    _privateTourUiState.update {
                        it.copy(
                            inputErrorType = inputError
                        )
                    }
                }
            }

        }
    }

    fun errorMessageShown() {
        _privateTourUiState.update {
            it.copy(
                isError = false,
                isLoadingData = false,
                inputErrorType = PrivateTourInputErrorType.NONE
            )
        }
    }
}