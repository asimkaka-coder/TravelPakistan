package com.tp.travelpakistan.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

enum class ProfileTabScreenType{
    BOOKINGS,PRIVATE_TOURS
}
data class TabRowItem(
    val title: String,
    val icon: ImageVector,
    val screenType: ProfileTabScreenType,
)

val tabRowItems = listOf(
    TabRowItem(
        title = "My Bookings",
        icon = Icons.Default.Bookmark,
        screenType = ProfileTabScreenType.BOOKINGS
    ),
    TabRowItem(
        title = "Private Tours",
        icon = Icons.Default.RemoveRedEye,
        screenType = ProfileTabScreenType.PRIVATE_TOURS

    )
)
