package com.tp.travelpakistan.ui.home.ui.model

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.SettingsSuggest
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.tp.travelpakistan.R

data class NavigationDrawerSection(
    val title: String,
    val navigationItems: List<NavigationDrawerItem>
)


val listOfSections = listOf<NavigationDrawerSection>(
    NavigationDrawerSection(
        title = "Company",
        navigationItems = listOf(
            NavigationDrawerItem(
                title = "About Us" ,
                icon = Icons.Default.Info,
                isSelected = false
            ),
            NavigationDrawerItem(
                title = "Contact Us" ,
                icon = Icons.Default.AccountBox,
            ),
            NavigationDrawerItem(
                title = "Rate Us" ,
                icon = Icons.Default.Star,
            )
        )
    ),
    NavigationDrawerSection(
        title = "Quick Access",
        navigationItems = listOf(
            NavigationDrawerItem(
                title = "Virtual Tour Guide" ,
                icon = Icons.Default.Info,
            ),
            NavigationDrawerItem(
                title = "Private Tour Request" ,
                icon = Icons.Default.AccountBox,
            ),
            NavigationDrawerItem(
                title = "Suggest me a Tour" ,
                icon = Icons.Default.SettingsSuggest,
            )
        )
    ),
    NavigationDrawerSection(
        title = "More",
        navigationItems = listOf(
            NavigationDrawerItem(
                title = "Privacy Policy" ,
                icon = Icons.Default.Info,
            ),
            NavigationDrawerItem(
                title = "Booking and Refund Policy" ,
                icon = Icons.Default.AccountBox,
            )
        )
    ),
)

