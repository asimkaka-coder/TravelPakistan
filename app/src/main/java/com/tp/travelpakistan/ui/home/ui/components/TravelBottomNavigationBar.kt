package com.tp.travelpakistan.ui.home.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


 val items = listOf<BottomNavigationItem>(
    BottomNavigationItem(
        itemName = "Home",
        icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "home")},
        isSelected = true
    ),
    BottomNavigationItem(
        itemName = "Saved",
        icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "saved") }
    ),
    BottomNavigationItem(
        itemName = "Account",
        icon = {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "accounf"
            )
        }
    )
)
@Composable
fun TravelBottomNavigationBar(
    items:List<BottomNavigationItem>,
    onNavItemSelected:(BottomNavigationItem)->Unit
) {


    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 0.dp
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = item.icon,
                label = {
                    Text(
                        item.itemName,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                },
                selected = item.isSelected,
                onClick = {
                          onNavItemSelected(item)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    )
            )
        }
    }
}
