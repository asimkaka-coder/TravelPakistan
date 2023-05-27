package com.tp.travelpakistan.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    innerPadding: PaddingValues? = null,
    GoToDetails: ((String) -> Unit)? = null
) {

    val userInfo = profileViewModel.userInfo.value

    val uiState = profileViewModel.profileUiState.collectAsState()
    var tabIndex by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = Unit, block = {
        profileViewModel.getPurchasedTikets()
    })

    Surface(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding!!)
                .padding(16.dp)
        ) {
            ProfileSection(
                userInfo
            )
            TabRow(
                selectedTabIndex = tabIndex,
                indicator = {
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(it[tabIndex]),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            ) {
                // Will be added later
                tabRowItems.forEachIndexed { index, tabRowItem ->
                    LeadingIconTab(
                        text = { Text(tabRowItem.title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        icon = {
                            Image(
                                tabRowItem.icon, null, colorFilter =
                                if (tabIndex == index) ColorFilter.tint(MaterialTheme.colorScheme.primary) else ColorFilter.tint(
                                    MaterialTheme.colorScheme.surfaceVariant
                                )
                            )
                        },
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
            when (tabRowItems[tabIndex].screenType) {
                ProfileTabScreenType.PRIVATE_TOURS -> {
                    PrivateRequestsTabScreen(requests = uiState.value.requestsData)
                }
                ProfileTabScreenType.BOOKINGS -> {
                    BookingsTabScreen(bookings = uiState.value.data, onCancelBooking = {
                        profileViewModel.cancelBooking(it)
                    }){
                        GoToDetails?.invoke(it)
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(innerPadding = PaddingValues(16.dp))
}