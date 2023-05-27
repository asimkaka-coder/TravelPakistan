package com.tp.travelpakistan.ui.booking.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.ui.graphics.vector.ImageVector

data class RadioItem(
    val label:String,
    val icons:List<ImageVector>,
    var isSelected:Boolean = false
)

val cardOptions = listOf(
    RadioItem(
        "Credit Card",
        icons = listOf(Icons.Default.CreditCard,Icons.Default.CreditCard),
        true
    ),
    RadioItem(
        "Debit Card",
        icons = listOf(Icons.Default.CreditCard),
        false
    ),
    RadioItem(
        "Master Card",
        icons = listOf(Icons.Default.CreditCard),
        false
    )
)