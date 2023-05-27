package com.tp.travelpakistan.ui.booking.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tp.travelpakistan.ui.booking.models.RadioItem
import com.tp.travelpakistan.ui.booking.models.cardOptions

@Composable
fun CardChoiceSection(
    cardOptions: List<RadioItem>,
    onCardSelection: (RadioItem) -> Unit
) {

    Box(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Payment Method",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items(cardOptions) {
                    TravelRadioButton(
                        it, onItemClick = { option -> onCardSelection(option) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CardChoiceSelectionPreview(){
    MaterialTheme(){
        CardChoiceSection(
            cardOptions
        ){

        }
    }
}