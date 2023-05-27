package com.tp.travelpakistan.ui.auth.data.models

import com.tp.travelpakistan.*

data class SignUpRequestBody(
    val username: String,
    val email: String,
    val password: String,
    val address: String,
    val phone: String,
    val cnic: String,
    val image: String
) {
    fun isValid(): InputFieldErrorType {
        if(username.isEmpty()) return InputFieldErrorType.Username
        if(!isValidEmail(email = email)) return InputFieldErrorType.Email
        if(!isValidCNIC(cnic = cnic)) return InputFieldErrorType.CNIC
        if(!isValidMobileNumber(mobileNumber = phone)) return InputFieldErrorType.PhoneNumber
        if(address.isEmpty()) return InputFieldErrorType.Address
        if(!isStrongPassword(password = password)) return InputFieldErrorType.Password
        return InputFieldErrorType.NONE
    }
}