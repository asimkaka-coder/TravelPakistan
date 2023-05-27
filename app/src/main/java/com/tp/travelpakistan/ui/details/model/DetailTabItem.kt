package com.tp.travelpakistan.ui.details.model

import com.tp.travelpakistan.ui.details.DetailTab

enum class DetailTabType{
    NONE,OVERVIEW,DETAILS,REVIEWS,OPTIONS,POLICY
}
data class DetailTabItem(
    val label:String,
    val isSelected:Boolean,
    val type:DetailTabType
)

val tabItems = listOf(
    DetailTabItem(
        label = "Overview",
        isSelected = true,
        DetailTabType.OVERVIEW
    ),
    DetailTabItem(
        label = "Details",
        isSelected = false,
        DetailTabType.DETAILS
    ),
    DetailTabItem(
        label = "Reviews",
        isSelected = false,DetailTabType.REVIEWS
    ),
    DetailTabItem(
        label = "Options",
        isSelected = false,
        DetailTabType.OPTIONS
    ),
    DetailTabItem(
        label = "Policy",
        isSelected = false,
        DetailTabType.POLICY
    ),
)