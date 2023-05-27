package com.tp.travelpakistan.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tp.travelpakistan.ui.home.ui.components.TourItem
import com.tp.travelpakistan.ui.home.ui.model.TourPackage
import com.tp.travelpakistan.ui.home.ui.model.listOfTours

@Composable
fun DetailTourSection(
    tourItem: TourPackage,
    onOrganizerTap:()->Unit={}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = tourItem.tourTitle,
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1
            )

            Text(
                modifier = Modifier.clickable {
                              onOrganizerTap()
                },
                text = "by ${tourItem.agencyName}",
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1
            )
        }

        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DetailTourRowItem(
                imageVector = Icons.Default.MyLocation,
                label = tourItem.tourDestination
            )
            DetailTourRowItem(
                imageVector = Icons.Default.Timer,
                label = tourItem.durationDays
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DetailTourRowItem(
                imageVector = Icons.Default.BusAlert,
                label = tourItem.capacity
            )
            DetailTourRowItem(
                imageVector = Icons.Default.Label,
                label = tourItem.pricePerPersion
            )
        }
    }
}

@Composable
fun DetailTourRowItem(
    imageVector: ImageVector,
    label: String
) {
    Row(
        modifier = Modifier.wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = imageVector,
            contentDescription = label,
            modifier = Modifier.size(18.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
        )
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 10.sp,
            ),
            maxLines = 1
        )
    }
}

@Preview
@Composable
fun DetailTourSectionPreview() {
    DetailTourSection(
        listOfTours[0]
    )
}