package com.tp.travelpakistan.ui.organizer.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector


enum class ScreenType {
    REVIEWS, TOURS
}

data class OrganizerTabRowItem(
    val title: String,
    val icon: ImageVector,
    val screenType: ScreenType
)


val organizerTabRowItems = listOf(
    OrganizerTabRowItem(
        title = "Tours",
        icon = Icons.Default.Bookmark,
        screenType = ScreenType.TOURS
    ),
    OrganizerTabRowItem(
        title = "Reviews",
        icon = Icons.Default.RemoveRedEye,
        screenType = ScreenType.REVIEWS
    )
)
