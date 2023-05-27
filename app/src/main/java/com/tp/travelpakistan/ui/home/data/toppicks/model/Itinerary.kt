package com.tp.travelpakistan.ui.home.data.toppicks.model

import com.google.gson.annotations.SerializedName

data class Itinerary(
    @SerializedName("Day 1") val Day1: String,
    @SerializedName("Day 2")val Day2: String,
    @SerializedName("Day 3")val Day3: String,
    @SerializedName("Day 4")val Day4: String,
    @SerializedName("Day 5")val Day5: String
)