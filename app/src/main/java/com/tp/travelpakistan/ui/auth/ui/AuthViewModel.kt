package com.tp.travelpakistan.ui.auth.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.tp.travelpakistan.InputFieldErrorType
import com.tp.travelpakistan.PreferenceManager
import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.auth.data.AuthRepository
import com.tp.travelpakistan.ui.auth.data.UserSession
import com.tp.travelpakistan.ui.auth.data.models.AuthResponse
import com.tp.travelpakistan.ui.auth.data.models.SignInRequestBody
import com.tp.travelpakistan.ui.auth.data.models.SignUpRequestBody
import com.tp.travelpakistan.ui.auth.data.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userSession: UserSession,
    private val preferenceManager: PreferenceManager
) : ViewModel() {
    private val _authUiState: MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState())
    val authUiState: StateFlow<AuthUiState> = _authUiState

init {
    checkCurrentLoggedInUser()
}
    fun checkCurrentLoggedInUser(){
        if (preferenceManager.isLoggedIn && preferenceManager.currentUser!=null){
            _authUiState.update {
                it.copy(isLoading = false, isError = false, isSuccess = true)
            }
            userSession.initUserSession(preferenceManager.currentUser)
        }
    }
    suspend fun registerUser(user: SignUpRequestBody) {
        when (user.isValid()) {
            InputFieldErrorType.NONE -> {
                _authUiState.update {
                    it.copy(isLoading = true)
                }
                when (val authResult = authRepository.signUpUser(user)) {
                    is Resource.Error -> {
                        Log.d("jejeAuth", "Data: ${authResult.data} Message:${authResult.message}")
                        _authUiState.update {
                            it.copy(isLoading = false, isError = true, isSuccess = false)
                        }

                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        Log.d("jejeAuth", "Data: ${authResult.data} Message")
                        _authUiState.update {
                            it.copy(isLoading = false, isError = false, isSuccess = true)
                        }
                        userSession.initUserSession(authResult.data!!.user)
                    }
                }
            }
            InputFieldErrorType.Address -> {
                _authUiState.update {
                    it.copy(inputErrorType = InputFieldErrorType.Address)
                }
            }
            InputFieldErrorType.CNIC -> {
                _authUiState.update {
                    it.copy(inputErrorType = InputFieldErrorType.CNIC)
                }
            }
            InputFieldErrorType.Email -> {
                _authUiState.update {
                    it.copy(inputErrorType = InputFieldErrorType.Email)
                }
            }
            InputFieldErrorType.Password -> {
                _authUiState.update {
                    it.copy(inputErrorType = InputFieldErrorType.Password)
                }
            }
            InputFieldErrorType.PhoneNumber -> {
                _authUiState.update {
                    it.copy(inputErrorType = InputFieldErrorType.PhoneNumber)
                }
            }
            InputFieldErrorType.Username -> {
                _authUiState.update {
                    it.copy(inputErrorType = InputFieldErrorType.Username)
                }
            }
        }
    }

    suspend fun authenticateUser(user: SignInRequestBody) {
        when (user.isValid()) {
            InputFieldErrorType.NONE -> {

                _authUiState.update {
                    it.copy(isLoading = true)
                }
                when (val authResult = authRepository.signInUser(user)) {
                    is Resource.Error -> {
                        Log.d("jejeAuth", "Data: ${authResult.data}")
                        _authUiState.update {
                            it.copy(isLoading = false, isError = true, isSuccess = false)
                        }

                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        Log.d("jejeAuth", "Data: ${authResult.data}")
                        _authUiState.update {
                            it.copy(isLoading = false, isError = false, isSuccess = true)
                        }
                        userSession.initUserSession(authResult.data!!.user)
                    }
                }
            }
            InputFieldErrorType.Username -> {
                _authUiState.update {
                    it.copy(inputErrorType = InputFieldErrorType.Username)
                }
            }
            InputFieldErrorType.Password -> {
                _authUiState.update {
                    it.copy(inputErrorType = InputFieldErrorType.Password)
                }
            }
            else -> {

            }
        }
    }

    fun authenticated() = _authUiState.update {
        it.copy(false, false, false, InputFieldErrorType.NONE)
    }
}