package com.tp.travelpakistan.ui.profile.privatetour

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PrivateTourRequest(
    privateTour: PrivateTour
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
                ) {
                Text(text = "Destination: ${privateTour.destination}", fontSize = 13.sp, maxLines = 1, fontWeight = FontWeight.Medium)
            }

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Departure: ${privateTour.departureDate}", fontSize = 13.sp, maxLines = 1, fontWeight = FontWeight.Medium)
                Text(text = "Travellers: ${privateTour.travelers}", fontSize = 13.sp, maxLines = 1, fontWeight = FontWeight.Medium)
            }

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Budget: ${privateTour.budget}", fontSize = 13.sp, maxLines = 1, fontWeight = FontWeight.Medium)
                Text(text = "Pickup: ${privateTour.departureLocation}", fontSize = 13.sp, maxLines = 1, fontWeight = FontWeight.Medium)
            }

            Text(text = "Description: ${privateTour.description}", fontSize = 12.sp, maxLines = 3, fontWeight = FontWeight.Medium)

        }
    }
}