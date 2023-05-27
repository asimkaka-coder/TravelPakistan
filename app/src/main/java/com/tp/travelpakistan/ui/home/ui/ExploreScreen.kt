package com.tp.travelpakistan.ui.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tp.travelpakistan.ui.home.HomeViewModel
import com.tp.travelpakistan.ui.home.ui.components.FilterChipSection
import com.tp.travelpakistan.ui.home.ui.components.SearchBar
import com.tp.travelpakistan.ui.home.ui.components.TourItem
import com.tp.travelpakistan.ui.home.ui.model.listOfFilters
import com.tp.travelpakistan.ui.home.ui.model.listOfTours

@Composable
fun ExploreScreen(
    exploreViewModel: ExploreViewModel = hiltViewModel(),
    innerPadding: PaddingValues? = null,
    GoToDetails: ((String) -> Unit)? = null,
) {

    val searchInput = rememberSaveable(stateSaver = TextFieldValue.Saver){
        mutableStateOf(
            TextFieldValue("")
        )
    }
    val uiState = exploreViewModel.homeUiState.collectAsState()
    val showFiltersDialog = remember {
        mutableStateOf(false)
    }

    val sliderValue = remember {
        mutableStateOf(100.0f..5000.0f)
    }

    if (showFiltersDialog.value) {
        FilterDialog(
            sliderRange = 100.0f..10000.0f,
            sliderValue = sliderValue.value,
            onValueSlide = {
                sliderValue.value = it
            }
        ) {
            showFiltersDialog.value = false
        }
    }

    Surface(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding!!)
                .padding(16.dp)
        ) {
            SearchBar(
                value = searchInput.value,
                placeholder = { Text(text = "Search Destination") },
                onValueChange = {
                    searchInput.value = it
                exploreViewModel.searchTours(searchInput.value.text,"a",1,1)
            })
//            FilterChipSection(
//                listOfFilters
//            ) { selectedFilter ->
//
//            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if(searchInput.value.text.isNotEmpty()) "Search Results For: ${searchInput.value.text}" else "",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily.Default,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text("Filters")
                    IconButton(
                        modifier = Modifier.size(28.dp),
                        onClick = {
                            /*** Open Filter Dialog***/
                            showFiltersDialog.value = true
                        },
                    ) {
                        Icon(Icons.Default.FilterAlt, "filter icon")
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            uiState.value.data.let {
                LazyColumn(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(it) { tour ->
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
    }
}


@Preview
@Composable
fun ExploreScreenPreview() {
    ExploreScreen(innerPadding = PaddingValues(8.dp))
}