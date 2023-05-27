package com.tp.travelpakistan.ui.suggestmetour


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tp.travelpakistan.ui.booking.components.TravelPakistanTextFieldV2


data class SuggestTourFormState(
    val destination: String,
    val days: String,
    val budget: String,
)

@Composable
fun SuggestTourForm(
    onSuggestTour: (destination: String, days: String, budget: String) -> Unit
) {

    val suggestFormState = remember {
        mutableStateOf(
            SuggestTourFormState(
                "", "", ""
            )
        )
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
                isLabelVisible = true,
                label = "Destination",
                value = suggestFormState.value.destination,
                placeholder = { Text("Select Destination") }
            ) {
                suggestFormState.value = suggestFormState.value.copy(destination = it)
            }

            TravelPakistanTextFieldV2(
                isLabelVisible = true,
                label = "Days",
                value = suggestFormState.value.days,
                placeholder = { Text("0") },
                keyboardType = KeyboardType.Decimal
            ) {
                suggestFormState.value = suggestFormState.value.copy(days = it)
            }

            TravelPakistanTextFieldV2(
                isLabelVisible = true,
                label = "Budget",
                value = suggestFormState.value.budget,
                keyboardType = KeyboardType.Decimal,
                placeholder = { Text("PKR 0") }
            ) {
                suggestFormState.value = suggestFormState.value.copy(budget = it)
            }

            Button(
                onClick = {
                    suggestFormState.value.let {
                        onSuggestTour(
                            it.destination, it.days, it.budget
                        )
                    }

                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Suggest Tour", style = TextStyle(fontSize = 18.sp))
            }
        }

    }
}


@Preview
@Composable
fun SuggestMeTourFormPreview() {
    MaterialTheme() {
        SuggestTourForm() { _, _, _ ->

        }
    }
}