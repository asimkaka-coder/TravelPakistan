package com.tp.travelpakistan.ui.organizer.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tp.travelpakistan.ui.home.ui.components.TravelTopBarV2
import com.tp.travelpakistan.ui.organizer.ui.components.ReviewScreen
import com.tp.travelpakistan.ui.organizer.ui.components.ToursScreen
import com.tp.travelpakistan.ui.profile.*

@ExperimentalMaterial3Api
@Composable
fun OrganizerScreen(
    organizerId:String="",
    organizerViewModel: OrganizerViewModel =  hiltViewModel(),
    navigateUp: (() -> Unit)? = null,
    GoToDetails:((String)->Unit)? = null,
) {

    var dataLoaded by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = dataLoaded) {
        if(!dataLoaded) {
            organizerViewModel.getOrganizerDetails(organizerId)
            dataLoaded = true
        }
    }


    val uiState  =organizerViewModel.homeUiState.collectAsState()
    var tabIndex by remember { mutableStateOf(0) }

Scaffold(
    topBar = {
        TravelTopBarV2(
            "Organizer Details",
            onBackPress = { navigateUp?.invoke() }
        )
    }
) {
    Surface(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(it)
    ) {
        uiState.value.data?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                OrganizerSection(
                    uiState.value.data?.organizer
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
                    organizerTabRowItems.forEachIndexed { index, tabRowItem ->
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
                when(organizerTabRowItems[tabIndex].screenType){
                    ScreenType.REVIEWS -> {
                        ReviewScreen(reviews = uiState.value.reviews?.reviews)
                    }
                    ScreenType.TOURS -> {
                        ToursScreen(tours = uiState.value.tours?.tours){
                            GoToDetails?.invoke(it)
                        }
                    }
                }
            }
        }

        if(uiState.value.isLoadingData){
            Box(modifier = Modifier.fillMaxSize()){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        if(uiState.value.isError){
            Box(modifier = Modifier.fillMaxSize()){
                Text(modifier = Modifier.align(Alignment.Center)
                , text = "Error Loading Data, try again"
                )
            }
        }

    }
}

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun OrganizerScreenPreview() {
    OrganizerScreen()
}