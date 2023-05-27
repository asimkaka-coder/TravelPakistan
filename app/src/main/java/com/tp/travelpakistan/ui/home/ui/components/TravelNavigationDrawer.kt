package com.tp.travelpakistan.ui.home.ui.components

import android.widget.ImageView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tp.travelpakistan.ui.home.ui.model.NavigationDrawerItem
import com.tp.travelpakistan.ui.home.ui.model.NavigationDrawerSection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Vector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelNavigationDrawerContent(
    items: List<NavigationDrawerSection>,
    onItemClick: (NavigationDrawerItem) -> Unit
) {
    ModalDrawerSheet {
        Spacer(Modifier.height(12.dp))
        NavigationDrawerHeader()
        items.forEach { navigationDrawerSection ->
            NavigationDrawerSection(
                navigationDrawerSection.title,
                navigationDrawerSection.navigationItems
            ) {
                onItemClick(it)
            }
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun NavigationDrawerSection(
    title: String,
    items: List<NavigationDrawerItem>,
    onItemClick: (NavigationDrawerItem) -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            text = title,
            style = TextStyle(
                fontSize = 18.sp
            )
        )
        items.forEach { item ->
            NavigationDrawerItem(
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.title) },
                selected = item.isSelected,
                onClick = { onItemClick(item) },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}


@Composable
fun NavigationDrawerHeader() {
    Text(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        text = "Travel Pakistan",
        style = TextStyle(
            fontSize = 18.sp
        )
    )
}