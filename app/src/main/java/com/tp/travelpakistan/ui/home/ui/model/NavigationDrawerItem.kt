package com.tp.travelpakistan.ui.home.ui.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationDrawerItem(
    val icon:ImageVector,
    val title:String,
    val isSelected:Boolean = false,
    val contentDescription:String = "Navigation Drawer Item"
)



