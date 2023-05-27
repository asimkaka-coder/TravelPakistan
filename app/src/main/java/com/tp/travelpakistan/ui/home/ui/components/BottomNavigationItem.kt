package com.tp.travelpakistan.ui.home.ui.components

import androidx.compose.runtime.Composable

data class BottomNavigationItem(
    val itemName:String,
    val icon: @Composable ()->Unit,
    var isSelected:Boolean = false
    )
