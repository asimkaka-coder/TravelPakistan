package com.tp.travelpakistan.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tp.travelpakistan.ui.home.ui.components.PopularTourItem
import com.tp.travelpakistan.ui.home.ui.components.TourItem
import com.tp.travelpakistan.ui.home.ui.model.TourPackage
import com.tp.travelpakistan.ui.home.ui.model.listOfTours

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    innerPadding: PaddingValues? = null,
    onToggleTheme: () -> Unit = {},
    GoToDetails: ((String) -> Unit)? = null,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val switchState = rememberSaveable {
        mutableStateOf(false)
    }

    val uiState = homeViewModel.homeUiState.collectAsState()

    Surface(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding!!)
                .padding(16.dp)
        ) {
            TopSection(switchState.value, homeViewModel.currentLoggedUser?.username, onToggleTheme)
            Spacer(modifier = Modifier.height(36.dp))
            PopularTourSection(GoToDetails= GoToDetails, uiState.value.data)
            Spacer(modifier = Modifier.height(36.dp))
            TopPickSection(GoToDetails = GoToDetails, uiState.value.data)
        }
    }
}


@Composable
fun TopSection(
    switchState: Boolean,
    user:String?,
    onToggleTheme: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(
                text = "Hello,",
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Text(
                text = "$user!",
                style = TextStyle(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Switch(
                checked = switchState,
                onCheckedChange = {
                    onToggleTheme.invoke()
                },
            )
            Text(
                text = "Toggle Theme",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    }
}

@Composable
fun TopPickSection(
    GoToDetails: ((String) -> Unit)?,
    tours: List<TourPackage>
) {
    Column {
        Text(
            text = "Top Picks For You",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(tours) { tour ->
                TourItem(
                    tourPackage = tour,
                    onTourClick = {
                        GoToDetails?.invoke(tour.tourId)
                    }
                )
            }
        }

    }
}

@Composable
fun PopularTourSection(
    GoToDetails: ((String) -> Unit)?,
    tours: List<TourPackage>
) {
    Column {
        Text(
            text = "Popular",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(tours) { tour ->
                PopularTourItem(tourPackage = tour, onClick = { GoToDetails?.invoke(tour.tourId) })
            }
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(onToggleTheme = {})
}