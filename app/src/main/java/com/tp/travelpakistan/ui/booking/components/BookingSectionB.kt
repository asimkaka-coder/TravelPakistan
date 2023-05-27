package com.tp.travelpakistan.ui.booking.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tp.travelpakistan.ui.booking.models.RadioItem
import com.tp.travelpakistan.ui.booking.models.cardOptions
import com.tp.travelpakistan.ui.home.ui.components.TourItem
import com.tp.travelpakistan.ui.home.ui.model.listOfTours

@Composable
fun BookingSectionB(
    cardOptions: List<RadioItem>,
    onCardSelected: (RadioItem) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            CardChoiceSection(
                cardOptions = cardOptions,
                onCardSelection = { cardSelected -> onCardSelected(cardSelected) }
            )
        }

    }
}

@Preview
@Composable
fun BookingSectionBPreview() {
    MaterialTheme()
    {
        BookingSectionB(
            cardOptions = cardOptions,
            onCardSelected = {}
        )
    }
}