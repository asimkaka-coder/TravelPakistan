package com.tp.travelpakistan.ui.home.ui.model

data class FilterCheckBox(
    val label:String,
    val isChecked:Boolean = false
)

val filterChecks = listOf(
    FilterCheckBox(
        label = "Less than 6 hours",
        isChecked = false
    ),
    FilterCheckBox(
        label = "12 to 18 hours",
        isChecked = true
    ),
    FilterCheckBox(
        label = "24 to 48 hours",
        isChecked = true
    ),
    FilterCheckBox(
        label = "3 days",
        isChecked = false
    ),
    FilterCheckBox(
        label = "1 week",
        isChecked = false
    ),
)