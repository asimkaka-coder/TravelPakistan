package com.tp.travelpakistan.ui.virtualtourguide.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.tp.travelpakistan.ui.home.ui.components.dummayImage
import com.tp.travelpakistan.ui.virtualtourguide.data.alltours.models.Destination


@Composable
fun DestinationItem(
    destination: Destination,
    goToDetails: (Destination) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                goToDetails(destination)
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.elevatedCardElevation()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.elevatedCardElevation(),
                shape = RoundedCornerShape(8.dp)
            ) {
                GlideImage(
                    imageModel =
                    { destination.images[0] },
                    modifier = Modifier
                        .fillMaxSize(),
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                )
            }

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        modifier =Modifier.weight(1f),
                        text = destination.location,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Text(
                        modifier =Modifier.weight(1f),
                        text = destination.destination,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.SemiBold
                        ),
                        textAlign =  TextAlign.End
                    )
                }

                Text(
                    text = destination.overview,
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Normal
                    ),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

    }
}

@Preview
@Composable
private fun DestinationItemPreview() {
    MaterialTheme {
        DestinationItem(
            destination = Destination(
                __v = 5750,
                _id = "augue",
                activities = listOf(),
                createdAt = "eu",
                culturalCusine = listOf(),
                culturalTraditions = listOf(),
                destination = "Hunza Valley",
                hiddenPlaces = listOf(),
                images = listOf(dummayImage),
                location = "Kashmir, Pakistan",
                overview = "qualisque asd asd  asd sa d as d as das dasdasdsa sadasd asd" +
                        "dasdsad das d as d asdasd as d asd asd sa d" +
                        "asd asdasdas" +
                        "asd as d as d asd as d as da sd" +
                        "dasd sd asd asd asd as",
                tags = listOf(),
                tips = listOf(),
                touristCount = 1212,
                updatedAt = "accusata",
                viewPoints = listOf()
            )
        )
    }
}