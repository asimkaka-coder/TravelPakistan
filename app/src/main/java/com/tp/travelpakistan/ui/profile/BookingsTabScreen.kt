package com.tp.travelpakistan.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tp.travelpakistan.ui.payment.ui.components.PurchaseTicket
import com.tp.travelpakistan.ui.payment.ui.components.PurchasedTicket
import com.tp.travelpakistan.ui.profile.privatetour.PrivateTour
import com.tp.travelpakistan.ui.profile.privatetour.PrivateTourRequest


@Composable
fun BookingsTabScreen(
    bookings: List<PurchasedTicket>,
    onCancelBooking: (bookingNumber:String) -> Unit = {},
    goToDetails: (tourId: String) -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(bookings) { puchasedTicket ->
                PurchaseTicket(purchasedTicket = puchasedTicket, onCancelBooking = {onCancelBooking(it)}) {
                    goToDetails(it)
                }
            }
        }

    }
}


@Composable
fun PrivateRequestsTabScreen(
    requests: List<PrivateTour>,
) {
    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(requests) { request ->
                PrivateTourRequest(request)
            }
        }

    }
}