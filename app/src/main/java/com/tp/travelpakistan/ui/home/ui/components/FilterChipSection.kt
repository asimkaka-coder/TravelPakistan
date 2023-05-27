package com.tp.travelpakistan.ui.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tp.travelpakistan.ui.home.ui.model.FilterItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipSection(
    items: List<FilterItem>,
    onFilterItemClick: (FilterItem) -> Unit
) {
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(4.dp)
            ){
        items(items) { item ->
            FilterChip(
                selected = item.isSelected,
                onClick = { onFilterItemClick(item) },
                label = { Text(item.label) },
                leadingIcon = if (item.isSelected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Localized Description",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = "Localized description",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                }
            )
        }
    }

}