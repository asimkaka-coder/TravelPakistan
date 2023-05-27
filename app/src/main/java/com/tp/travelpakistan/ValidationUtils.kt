package com.tp.travelpakistan

import android.util.Patterns



sealed interface PaymentInputErrorType{
    object NONE:PaymentInputErrorType
    object CardHolderName:PaymentInputErrorType
    object CardNumber:PaymentInputErrorType
    object ExpiryDate:PaymentInputErrorType
    object CVV:PaymentInputErrorType
    object PostalCode:PaymentInputErrorType
    object Country:PaymentInputErrorType



}

sealed interface PrivateTourInputErrorType{
    object NONE:PrivateTourInputErrorType

    object Destination:PrivateTourInputErrorType

    object EstimatedDays:PrivateTourInputErrorType

    object Budget:PrivateTourInputErrorType
    object Route:PrivateTourInputErrorType
    object NumberOfTravellers:PrivateTourInputErrorType
    object DepartureDate:PrivateTourInputErrorType
    object DepartureTime:PrivateTourInputErrorType
    object PickupLocation:PrivateTourInputErrorType
    object AdditionalRequirements:PrivateTourInputErrorType

}



sealed interface SuggestInputErrorType{
    object NONE:SuggestInputErrorType

    object Destination:SuggestInputErrorType

    object Days:SuggestInputErrorType

    object Budget:SuggestInputErrorType
}
sealed interface InputFieldErrorType{
    object NONE:InputFieldErrorType
    object Username:InputFieldErrorType
    object Email:InputFieldErrorType
    object Password:InputFieldErrorType
    object CNIC:InputFieldErrorType
    object PhoneNumber:InputFieldErrorType
    object Address:InputFieldErrorType


}


fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("""^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$""")
    return emailRegex.matches(email)
}



fun isStrongPassword(password: String): Boolean {
    val upperCaseRegex = Regex("[A-Z]")
    val lowerCaseRegex = Regex("[a-z]")
    val digitRegex = Regex("\\d")
    val specialCharRegex = Regex("[^\\w\\s]")

    return password.length >= 8 &&
            upperCaseRegex.containsMatchIn(password) &&
            lowerCaseRegex.containsMatchIn(password) &&
            digitRegex.containsMatchIn(password) &&
            specialCharRegex.containsMatchIn(password)
}

fun isValidMobileNumber(mobileNumber: String): Boolean {
    val mobileNumberRegex = Regex("^03[0-9]{2}[0-9]{7}\$")
    return mobileNumberRegex.matches(mobileNumber)
}


fun isValidCNIC(cnic: String): Boolean {
    val cnicRegex = Regex("^\\d{5}-\\d{7}-\\d{1}\$")
    return cnicRegex.matches(cnic)
}


fun isValidSuggestTourRequest(
    destination:String,
    days:String,
    budget:String
): SuggestInputErrorType {
    if(destination.isEmpty()) return SuggestInputErrorType.Destination
    if(days.isEmpty() || days.toIntOrNull() == null) return SuggestInputErrorType.Days
    if(budget.isEmpty() || budget.toIntOrNull() == null) return SuggestInputErrorType.Budget
    return SuggestInputErrorType.NONE
}

fun validateCreditCardNumber(creditCardNumber: String): Boolean {
    // Check that the input contains only digits and has a length between 13 and 19, with optional dashes
    if (!creditCardNumber.matches("^\\d{4}[- ]?\\d{4}[- ]?\\d{4}[- ]?\\d{4}$".toRegex())) {
        return false
    }

    // Clean the input by removing spaces and dashes
    val cleanedInput = creditCardNumber.replace("\\s|-".toRegex(), "")

    // Reverse the input and convert each character to an integer
    val reversedInput = cleanedInput.reversed()
    val digits = reversedInput.map { it.toString().toInt() }

    // Apply the Luhn algorithm
    val sum = digits.mapIndexed { index, digit ->
        if (index % 2 == 0) digit else (digit * 2).let { if (it > 9) it - 9 else it }
    }.sum()

    // Return true if the sum is divisible by 10, false otherwise
    return sum % 10 == 0
}

fun validateCVV(cvv: String): Boolean {
    // Check that the input contains only digits and has a length of 3
    if (!cvv.matches("\\d{3}".toRegex())) {
        return false
    }

    return true
}


fun main(){
    println(isValidEmail("anasgmail.com"))
    println(isStrongPassword("Binomialtheorem)123"))
    println(isValidMobileNumber("03461753321"))
    println(isValidCNIC("33302-5158556-5"))
}