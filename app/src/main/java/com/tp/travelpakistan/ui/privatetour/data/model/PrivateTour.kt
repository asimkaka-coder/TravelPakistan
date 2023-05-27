package com.tp.travelpakistan.ui.privatetour.data.model

data class PrivateTour(
    val __v: Int,
    val _id: String,
    val bid: List<Any>,
    val budget: Int,
    val createdAt: String,
    val departureDate: String,
    val departureLocation: String,
    val departureTime: String,
    val description: String,
    val destination: String,
    val durationDays: Int,
    val organizer: Any,
    val price: Int,
    val route: List<String>,
    val status: String,
    val travelers: Int,
    val updatedAt: String,
    val user: String
)