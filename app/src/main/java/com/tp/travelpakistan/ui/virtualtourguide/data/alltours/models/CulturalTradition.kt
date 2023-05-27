package com.tp.travelpakistan.ui.virtualtourguide.data.alltours.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CulturalTradition(
    val _id: String,
    val description: String,
    val name: String
): Parcelable