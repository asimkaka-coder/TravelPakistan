package com.tp.travelpakistan.ui.privatetour.data.model

import com.google.gson.annotations.SerializedName

data class PrivateTourRequestBody(
    @SerializedName("user") val user: String,
    @SerializedName("destination") val destination: String,
    @SerializedName("route") val route: List<String>,
    @SerializedName("durationDays") val durationDays: Int,
    @SerializedName("travelers") val travelers: Int,
    @SerializedName("description") val description: String,
    @SerializedName("departureDate") val departureDate: String,
    @SerializedName("departureTime") val departureTime: String,
    @SerializedName("departureLocation") val departureLocation: String,
    @SerializedName("budget") val budget: Int
)