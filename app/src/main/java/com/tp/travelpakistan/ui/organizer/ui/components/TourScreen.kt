package com.tp.travelpakistan.ui.organizer.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tp.travelpakistan.ui.home.data.toppicks.model.Tour
import com.tp.travelpakistan.ui.home.ui.components.TourItem
import com.tp.travelpakistan.ui.organizer.data.models.Review


@Composable
fun ToursScreen(
    tours: List<Tour>?,
    onTourClick:(String)->Unit={}
) {
    if (tours.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "No Tours Found",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(tours) { tour ->
                    TourItem(tourPackage = tour.toTourPackage(),
                    onTourClick = {
                        onTourClick(it.tourId)
                    })
                }
            }
        }
    }
}