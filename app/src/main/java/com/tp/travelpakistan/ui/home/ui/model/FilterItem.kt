package com.tp.travelpakistan.ui.home.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.graphics.vector.ImageVector

data class FilterItem(
    val label:String,
    val icon:ImageVector,
    val isSelected:Boolean = false
)

val listOfFilters = listOf(
    FilterItem(
        label = "Stay",
        icon = Icons.Default.Add
    ),
    FilterItem(
        label = "North",
        icon = Icons.Default.Add
    ),
    FilterItem(
        label = "South",
        icon = Icons.Default.Add,
        isSelected = false
    ),
    FilterItem(
        label = "Hilly",
        icon = Icons.Default.Add
    ),
    FilterItem(
        label = "Snow",
        icon = Icons.Default.Add
    ),
    FilterItem(
        label = "Hike",
        icon = Icons.Default.Add
    ),
)