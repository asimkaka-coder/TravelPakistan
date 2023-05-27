package com.tp.travelpakistan.ui.auth.ui

import com.tp.travelpakistan.InputFieldErrorType
import com.tp.travelpakistan.ui.auth.data.models.AuthResponse

data class AuthUiState(
    val isLoading: Boolean = false,
    val isError:Boolean = false,
    val isSuccess:Boolean=false,
    val inputErrorType:InputFieldErrorType = InputFieldErrorType.NONE
    )