package com.tp.travelpakistan.ui.privatetour.ui


import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.tp.travelpakistan.PrivateTourInputErrorType
import com.tp.travelpakistan.formatDate
import com.tp.travelpakistan.ui.booking.components.TravelPakistanTextFieldV2
import com.tp.travelpakistan.ui.privatetour.data.model.PrivateTourRequestBody
import java.text.SimpleDateFormat
import java.util.*


data class PrivateTourFormState(
    val destination: String,
    val estimatedDays: String,
    val budget: String,
    val route: String,
    val numberOgTravellers: String,
    val departureDate: String,
    val departureTime: String,
    val pickupLocation: String,
    val additionalRequirements: String,
) {

    fun isValid(): PrivateTourInputErrorType {
        if (destination.isEmpty()) return PrivateTourInputErrorType.Destination
        if (estimatedDays.isEmpty() || estimatedDays.toIntOrNull() == null) return PrivateTourInputErrorType.EstimatedDays
        if (budget.isEmpty() || budget.toIntOrNull() == null) return PrivateTourInputErrorType.Budget
        if (route.isEmpty()) return PrivateTourInputErrorType.Route
        if (numberOgTravellers.isEmpty() || numberOgTravellers.toIntOrNull() == null) return PrivateTourInputErrorType.NumberOfTravellers
        if (departureDate.isEmpty()) return PrivateTourInputErrorType.DepartureDate
        if (departureTime.isEmpty()) return PrivateTourInputErrorType.DepartureTime
        if (pickupLocation.isEmpty()) return PrivateTourInputErrorType.PickupLocation
        if (additionalRequirements.isEmpty()) return PrivateTourInputErrorType.AdditionalRequirements
        return PrivateTourInputErrorType.NONE

    }

    fun toPrivateTourRequestBody(userId: String): PrivateTourRequestBody {
        return PrivateTourRequestBody(
            budget = budget.toInt(),
            departureDate = departureDate,
            departureTime = departureTime,
            departureLocation = pickupLocation,
            destination = destination,
            description = additionalRequirements,
            travelers = numberOgTravellers.toInt(),
            route = route.split(","),
            durationDays = estimatedDays.toInt(),
            user = userId,
        )
    }
}

sealed interface PrivateTourFormValueChange {
    data class Destination(val newValue: String) : PrivateTourFormValueChange
    data class EstimatedDays(val newValue: String) : PrivateTourFormValueChange

    data class Budget(val newValue: String) : PrivateTourFormValueChange
    data class Route(val newValue: String) : PrivateTourFormValueChange
    data class NumberOfTravellers(val newValue: String) : PrivateTourFormValueChange
    data class DepartureDate(val newValue: String) : PrivateTourFormValueChange
    data class DepartureTime(val newValue: String) : PrivateTourFormValueChange
    data class PickupLocation(val newValue: String) : PrivateTourFormValueChange

    data class AdditionalRequirements(val newValue: String) : PrivateTourFormValueChange

}

@Composable
fun TimePickerDialog(
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = {
            onCancel()
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            usePlatformDefaultWidth = true,
            decorFitsSystemWindows = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            elevation = CardDefaults.elevatedCardElevation(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                content()
                Button(onClick = { onConfirm() }) {
                    Text(text = "Confirm")
                }
            }
        }

    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivateTourForm(
    privateTourFormState: PrivateTourFormState,
    onValueChange: (PrivateTourFormValueChange) -> Unit
) {

    val showDatePickerDialog = remember { mutableStateOf(false) }
    val showTimePickerDialog = remember { mutableStateOf(false) }
    val formatter = remember { SimpleDateFormat("hh:mm:ss a", Locale.getDefault()) }
    val state = rememberTimePickerState(
    )

    if (showTimePickerDialog.value) {
        TimePickerDialog(
            onCancel = { showTimePickerDialog.value = false },
            onConfirm = {
                val cal = Calendar.getInstance()
                cal.set(Calendar.HOUR_OF_DAY, state.hour)
                cal.set(Calendar.MINUTE, state.minute)
                cal.set(Calendar.SECOND, 0)
                cal.isLenient = false

                Log.d("jeje", "Entered time: ${formatter.format(cal.time)}")
                onValueChange(PrivateTourFormValueChange.DepartureTime(formatter.format(cal.time)))
                showTimePickerDialog.value = false
            },
        ) {
            TimePicker(state = state)
        }
    }

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
                        Log.d(
                            "Selected date timestamp:",
                            " ${datePickerState.selectedDateMillis?.formatDate()}"
                        )
                        datePickerState.selectedDateMillis?.let {
                            onValueChange(PrivateTourFormValueChange.DepartureDate(it.formatDate()))
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
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
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
                value = privateTourFormState.destination,
                placeholder = { Text("Where do you wanna go?") }
            ) {
                onValueChange(PrivateTourFormValueChange.Destination(it))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                TravelPakistanTextFieldV2(
                    value = privateTourFormState.estimatedDays,
                    modifier = Modifier.width(160.dp),
                    label = "Estimated Days",
                    isLabelVisible = true,
                    placeholder = { Text("0") },
                    keyboardType = KeyboardType.Decimal
                ) {
                    onValueChange(PrivateTourFormValueChange.EstimatedDays(it))

                }

                TravelPakistanTextFieldV2(
                    value = privateTourFormState.budget,
                    modifier = Modifier.width(160.dp),
                    label = "Budget",
                    isLabelVisible = true,
                    placeholder = { Text("PKR 0") },
                    keyboardType = KeyboardType.Decimal

                ) {
                    onValueChange(PrivateTourFormValueChange.Budget(it))

                }

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                TravelPakistanTextFieldV2(
                    value = privateTourFormState.route,
                    modifier = Modifier.width(160.dp),
                    label = "Route",
                    isLabelVisible = true,
                    placeholder = { Text("Specify Route") }
                ) {
                    onValueChange(PrivateTourFormValueChange.Route(it))

                }

                TravelPakistanTextFieldV2(
                    value = privateTourFormState.numberOgTravellers,
                    modifier = Modifier.width(160.dp),
                    label = "Number of Travellers",
                    isLabelVisible = true,
                    placeholder = { Text("0") },
                    keyboardType = KeyboardType.Decimal


                ) {
                    onValueChange(PrivateTourFormValueChange.NumberOfTravellers(it))

                }

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                TravelPakistanTextFieldV2(
                    value = privateTourFormState.departureDate,
                    modifier = Modifier.width(160.dp),
                    isIconVisible = true,
                    leadingIcon = Icons.Default.EditCalendar,
                    label = "Departure Date",
                    isLabelVisible = true,
                    placeholder = { Text("dd-mm-yyyy") },
                    keyboardType = KeyboardType.Decimal,
                    clickableField = true,
                    onTextFieldClick = {
                        showDatePickerDialog.value = true
                    }
                ) {
                }

                TravelPakistanTextFieldV2(
                    value = privateTourFormState.departureTime,
                    modifier = Modifier.width(160.dp),
                    label = "Departure Time",
                    isLabelVisible = true,
                    placeholder = { Text("XX:XX:XX") },
                    clickableField = true,
                    onTextFieldClick = {
                        showTimePickerDialog.value = true
                    }

                ) {
                    onValueChange(PrivateTourFormValueChange.DepartureTime(it))

                }

            }

            TravelPakistanTextFieldV2(
                value = privateTourFormState.pickupLocation,
                label = "Pickup Location",
                isLabelVisible = true,
                placeholder = { Text("Enter Location") }

            ) {
                onValueChange(PrivateTourFormValueChange.PickupLocation(it))

            }

            TravelPakistanTextFieldV2(
                value = privateTourFormState.additionalRequirements,
                label = "Additional Requirements",
                isLabelVisible = true,
                placeholder = { Text(".......................") }

            ) {
                onValueChange(PrivateTourFormValueChange.AdditionalRequirements(it))

            }
        }

    }
}


@Preview
@Composable
fun PrivateTourFormPreview() {
    MaterialTheme() {
//        PrivateTourForm(){
//
//        }
    }
}