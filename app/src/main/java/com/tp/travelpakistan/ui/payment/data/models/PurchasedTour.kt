package com.tp.travelpakistan.ui.payment.data.models

data class PurchasedTour(
    val __v: Int,
    val _id: String,
    val amount: Int,
    val createdAt: String,
    val pickup: String,
    val purchasedBy: String,
    val tour: String,
    val travellers: Travellers,
    val updatedAt: String
)