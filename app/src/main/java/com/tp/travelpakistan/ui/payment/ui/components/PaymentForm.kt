package com.tp.travelpakistan.ui.payment

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.travelpakistan.PaymentInputErrorType
import com.tp.travelpakistan.formatDate
import com.tp.travelpakistan.ui.booking.components.TravelPakistanTextFieldV2
import com.tp.travelpakistan.ui.theme.surfaceColor
import com.tp.travelpakistan.validateCVV
import com.tp.travelpakistan.validateCreditCardNumber


enum class PaymentInputType{
    HOLDER_NAME,CARD_NUMBER,EXPIRATION_DATE,CVV,COUNTRY,POSTSAL_CODE
}
data class PaymentFormState(
    val cardHolderName: String,
    val cardNumber: String,
    val expirationDate: String,
    val CVV: String,
    val country: String,
    val postalCode: String
){
    fun isValid():PaymentInputErrorType{
        if(cardHolderName.isEmpty()) return PaymentInputErrorType.CardHolderName
        if(!validateCreditCardNumber(cardNumber)) return PaymentInputErrorType.CardNumber
        if(expirationDate.isEmpty()) return PaymentInputErrorType.ExpiryDate
        if(!validateCVV(CVV)) return PaymentInputErrorType.CVV
        if(country.isEmpty()) return PaymentInputErrorType.Country
        if(postalCode.isEmpty()) return PaymentInputErrorType.PostalCode
        return PaymentInputErrorType.NONE
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun PaymentForm(
    paymentFormState: PaymentFormState,
    onFormStateChange:(String, PaymentInputType) -> Unit
) {

    val showDatePickerDialog = remember { mutableStateOf(false) }
    if (showDatePickerDialog.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
        DatePickerDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                showDatePickerDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePickerDialog.value = false
                        Log.d("Selected date timestamp:", " ${datePickerState.selectedDateMillis?.formatDate()}")
                        datePickerState.selectedDateMillis?.let {
                            onFormStateChange(it.formatDate(),PaymentInputType.EXPIRATION_DATE)
                        }
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePickerDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }

    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.elevatedCardElevation()

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TravelPakistanTextFieldV2(
                value = paymentFormState.cardHolderName,
                label = "Card Holder Name",
                isLabelVisible = true,
                placeholder = { Text("Full Name") }
            ) {
                onFormStateChange(it,PaymentInputType.HOLDER_NAME)
            }

            TravelPakistanTextFieldV2(
                value = paymentFormState.cardNumber,
                label = "Card Number",
                isLabelVisible = true,
                placeholder = { Text("XXXX-XXXX-XXXX-XXXX") },
                keyboardType = KeyboardType.Decimal
            ){
                onFormStateChange(it,PaymentInputType.CARD_NUMBER)

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                TravelPakistanTextFieldV2(
                    value = paymentFormState.expirationDate,
                    modifier = Modifier.width(155.dp),
                    label = "Expiration Date",
                    isLabelVisible = true,
                    placeholder = { Text("yyyy-mm-dd") },
                    keyboardType = KeyboardType.Decimal,
                    clickableField = true,
                    onTextFieldClick = {
                        showDatePickerDialog.value = true
                    }
                ){
                    onFormStateChange(it,PaymentInputType.EXPIRATION_DATE)

                }

                TravelPakistanTextFieldV2(
                    value = paymentFormState.CVV,
                    modifier = Modifier.width(155.dp),
                    label = "CVV",
                    isLabelVisible = true,
                    placeholder = { Text("XXX") },
                    keyboardType = KeyboardType.Decimal

                ){
                    onFormStateChange(it,PaymentInputType.CVV)

                }

            }

            TravelPakistanTextFieldV2(
                value = paymentFormState.country,
                label = "Country",
                isLabelVisible = true,
                placeholder = { Text("Country") }

            ){
                onFormStateChange(it,PaymentInputType.COUNTRY)

            }

            TravelPakistanTextFieldV2(
                value = paymentFormState.postalCode,
                label = "Postal Code",
                isLabelVisible = true,
                placeholder = { Text("00000") },
                keyboardType = KeyboardType.Decimal

            ){
                onFormStateChange(it,PaymentInputType.POSTSAL_CODE)
            }
        }

    }
}


@Preview
@Composable
fun PaymentFormPreview() {
//    PaymentForm()
}