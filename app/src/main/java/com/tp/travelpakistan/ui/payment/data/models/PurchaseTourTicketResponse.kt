package com.tp.travelpakistan.ui.payment.data.models

data class PurchaseTourTicketResponse(
    val message: String,
    val tours: List<PurchasedTourTicket>
)