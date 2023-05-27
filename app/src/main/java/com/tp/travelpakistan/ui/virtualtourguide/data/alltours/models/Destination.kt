package com.tp.travelpakistan.ui.virtualtourguide.data.alltours.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Destination(
    val __v: Int,
    val _id: String,
    val activities: List<Activity>,
    val createdAt: String,
    val culturalCusine: List<CulturalCusine>,
    val culturalTraditions: List<CulturalTradition>,
    val destination: String,
    val hiddenPlaces: List<HiddenPlace>,
    val images: List<String>,
    val location: String,
    val overview: String,
    val tags: List<String>,
    val tips: List<String>,
    val touristCount: Int,
    val updatedAt: String,
    val viewPoints: List<ViewPoint>
):Parcelable