package com.tp.travelpakistan.ui.payment.data.models

data class PurchaseTourRequestBody(
    val amount: Int,
    val pickup: String,
    val purchasedBy: String,
    val tour: String,
    val travellers: Travellers
)