package com.tp.travelpakistan.ui.payment.data.models

import com.tp.travelpakistan.ui.home.data.toppicks.model.Tour

data class PurchasedTourTicket(
    val __v: Int,
    val _id: String,
    val amount: Int,
    val createdAt: String,
    val pickup: String,
    val purchasedBy: String,
    val tour: Tour,
    val travellers: Travellers,
    val updatedAt: String
)