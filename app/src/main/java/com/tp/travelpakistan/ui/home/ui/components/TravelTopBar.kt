package com.tp.travelpakistan.ui.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun TravelTopBar(
    onMenuClick: () -> Unit,
    onLogoutSession: () -> Unit,
    onUserClick: () -> Unit
) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Travel Pakistan",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = FontFamily.Cursive,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            IconButton(
                onClick = onLogoutSession
            ) {
                Icon(
                    imageVector = Icons.Rounded.Logout,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .background(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary
                        )
                        .padding(1.dp)

                )
            }
            IconButton(onClick = onUserClick) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .background(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary
                        )
                        .padding(1.dp)
                )
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelTopBarV2(
    title: String,
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit,
) {

    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontFamily = FontFamily.SansSerif,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
    )
}