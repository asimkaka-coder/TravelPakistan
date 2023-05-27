@file:OptIn(ExperimentalMaterial3Api::class)

package com.tp.travelpakistan.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tp.travelpakistan.*
import com.tp.travelpakistan.ui.home.HomeScreen
import com.tp.travelpakistan.ui.home.ui.ExploreScreen
import com.tp.travelpakistan.ui.home.ui.components.TravelBottomNavigationBar
import com.tp.travelpakistan.ui.home.ui.components.TravelNavigationDrawerContent
import com.tp.travelpakistan.ui.home.ui.components.TravelTopBar
import com.tp.travelpakistan.ui.home.ui.components.items
import com.tp.travelpakistan.ui.home.ui.model.listOfSections
import com.tp.travelpakistan.ui.profile.ProfileScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelPakistan(
    onToggleTheme: () -> Unit,
    travelViewModel: TravelViewModel = hiltViewModel(),
    onLogoutSession: () -> Unit = {},
    GoToPrivateRequest: (() -> Unit)? = null,
    GoToSuggestMeTour: (() -> Unit)? = null,
    GoToVirtualTourGuide: (() -> Unit)? = null,
    GoToDetails: ((String) -> Unit)? = null,
) {

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            TravelNavigationDrawerContent(listOfSections) { selectedItem ->
                if (selectedItem.title == "Private Tour Request") {
                    GoToPrivateRequest?.invoke()
                }
                if (selectedItem.title == "Suggest me a Tour") {
                    GoToSuggestMeTour?.invoke()
                }

                if (selectedItem.title == "Virtual Tour Guide") {
                    GoToVirtualTourGuide?.invoke()
                }

                if (selectedItem.title == "Rate Us") {
                    context.rateUsDialog {

                    }
                }

                if (selectedItem.title == "About Us") {
                    context.openLink(ABOUT_US_URL)
                }

                if (selectedItem.title == "Contact Us") {
                    context.openLink(CONTACT_US_URL)
                }
                if (selectedItem.title == "Privacy Policy") {
                    context.toast("Will be added soon!")
                }
                if (selectedItem.title == "Booking and Refund Policy") {
                    context.toast("Will be added soon!")
                }
                scope.launch { drawerState.close() }
            }
        },
        content = {
            Surface(
                modifier = Modifier.background(MaterialTheme.colorScheme.background)
            ) {
                Scaffold(
                    topBar = {
                        TravelTopBar(
                            onMenuClick = {
                                scope.launch { drawerState.open() }
                            },
                            onLogoutSession = {
                                travelViewModel.logoutUser()
                                onLogoutSession()
                            },
                            onUserClick = {
                                travelViewModel.onBottomNavItemClick(
                                    travelViewModel.bottomNavItems.value.get(
                                        2
                                    )
                                )
                            }
                        )
                    },
                    content = { innerPadding ->
                        when (travelViewModel.selectedBottomItem.value.itemName) {
                            "Home" -> {
                                HomeScreen(innerPadding, onToggleTheme, GoToDetails)
                            }
                            "Explore" -> {
                                ExploreScreen(
                                    innerPadding = innerPadding,
                                    GoToDetails = GoToDetails
                                )
                            }
                            "Account" -> {
                                ProfileScreen(
                                    innerPadding = innerPadding,
                                    GoToDetails = GoToDetails
                                )
                            }
                        }
                    },
                    bottomBar = {
                        TravelBottomNavigationBar(items = travelViewModel.bottomNavItems.value) { selectedItem ->
                            travelViewModel.onBottomNavItemClick(selectedItem)
                        }
                    }
                )
            }

        })
}