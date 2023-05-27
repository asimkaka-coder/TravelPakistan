package com.tp.travelpakistan.ui.auth.data.models

import com.google.gson.annotations.SerializedName
import com.tp.travelpakistan.InputFieldErrorType


data class SignInRequestBody(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
){
    fun isValid(): InputFieldErrorType {
        if(username.isEmpty()) return InputFieldErrorType.Username
        if(password.isEmpty()) return InputFieldErrorType.Password
        return InputFieldErrorType.NONE
    }
}