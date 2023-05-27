package com.tp.travelpakistan.ui.payment.ui.components

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tp.travelpakistan.R
import com.tp.travelpakistan.ui.TicketShape
import com.tp.travelpakistan.ui.drawTicketPath
import com.tp.travelpakistan.ui.theme.md_theme_light_primary


data class TicketItem(
    val firstItem: Pair<String, String>,
    val secondItem: Pair<String, String>
)


data class PurchasedTicket(
    val username: String,
    val status: String,
    val ticketItems: List<TicketItem>,
    val bookingNumber: String,
)

@Composable
fun PurchaseTicket(
    purchasedTicket: PurchasedTicket,
    onCancelBooking: (bookingNumber: String) -> Unit = {},
    goToTourDetails: (tourId: String) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = TicketShape(24.dp.toPx())
                clip = true
            }
            .background(MaterialTheme.colorScheme.background)
            .drawBehind {
                scale(scale = 0.9f) {
                    drawPath(
                        path = drawTicketPath(size = size, cornerRadius = 24.dp.toPx()),
                        color = md_theme_light_primary,
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                        )
                    )
                }
            }
            .padding(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TicketHeader(username = purchasedTicket.username, status = purchasedTicket.status)
            purchasedTicket.ticketItems.forEach {
                TicketSectionItem(ticketItem = it)
            }
            TicketFooterSection(bookingNumber = purchasedTicket.bookingNumber, onViewDetails = {
                goToTourDetails(
                    purchasedTicket.ticketItems.first().firstItem.second
                )
            }, onCancelBooking = {
                onCancelBooking(it)
            })
        }
    }
}


@Composable
fun TicketFooterSection(
    bookingNumber: String,
    onViewDetails: (String) -> Unit = {},
    onCancelBooking: (bookingNumber: String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Booking Number", style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        )

        Text(
            text = bookingNumber, style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                modifier = Modifier
                    .wrapContentWidth()
                    .height(40.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                onClick = { onCancelBooking(bookingNumber) },
            ) {
                Text(
                    "Cancel",
                    style = TextStyle(fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
                )
            }
            Button(
                onClick = {
                    onViewDetails(bookingNumber)
                },
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .wrapContentWidth()
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Details", style = TextStyle(fontSize = 16.sp))
            }

        }
    }
}

@Composable
fun TicketSectionItem(
    ticketItem: TicketItem
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = ticketItem.firstItem.first, style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )

            Text(
                text = ticketItem.secondItem.first, style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = ticketItem.firstItem.second, style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )

            Text(
                text = ticketItem.secondItem.second, style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }

    }
}


@Composable
fun TicketHeader(
    username: String,
    status: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.travel_pakistan_logo),
            contentDescription = null
        )

        Text(
            text = username, style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
        )

        Text(
            modifier = Modifier
                .background(
                    MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(4.dp),
            text = status, style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onPrimary
            )
        )


    }
}


@Preview
@Composable
private fun PurchasedTicketPreview() {
    MaterialTheme {
        PurchaseTicket(
            purchasedTicket = PurchasedTicket(
                "User Name", "Booked", listOf(
                    TicketItem(
                        Pair("Tour Name", "Hunza Valley"),
                        Pair("Travellers", "2 Adults & 1 Child")
                    ),
                    TicketItem(
                        Pair("Pickup", "Lahore"),
                        Pair("Amount", "PKR 250000")
                    ),
                ),
                "2372372838239"
            )
        )
    }
}