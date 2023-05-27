package com.tp.travelpakistan.ui.virtualtourguide.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tp.travelpakistan.R
import com.tp.travelpakistan.ui.details.DetailImageSlider
import com.tp.travelpakistan.ui.details.DetailTourSection
import com.tp.travelpakistan.ui.details.components.*
import com.tp.travelpakistan.ui.details.data.cancellationPolicies
import com.tp.travelpakistan.ui.details.data.childPolicies
import com.tp.travelpakistan.ui.details.data.refundPolicies
import com.tp.travelpakistan.ui.details.model.*
import com.tp.travelpakistan.ui.home.ui.components.TravelItemChip
import com.tp.travelpakistan.ui.home.ui.components.TravelTopBarV2
import com.tp.travelpakistan.ui.home.ui.model.TourPackage
import com.tp.travelpakistan.ui.home.ui.model.listOfTours
import com.tp.travelpakistan.ui.profile.TabRowItem
import com.tp.travelpakistan.ui.virtualtourguide.data.alltours.models.Destination
import com.tp.travelpakistan.ui.virtualtourguide.ui.components.DestinationSlideSection
import com.tp.travelpakistan.ui.virtualtourguide.ui.components.DetailDestinationSection
import com.tp.travelpakistan.ui.virtualtourguide.ui.components.SlideItem
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun DestinationDetailScreen(
    virtualTourGuideViewModel: VirtualTourGuideViewModel = hiltViewModel(),
    selectedDestination: Destination?,
    navigateUp: (() -> Unit)? = null,
) {
    var showCompleteOverview = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val bottomSheetShowItem = remember {
        mutableStateOf(DetailTabType.NONE)
    }

    val uiState = virtualTourGuideViewModel.homeUiState.collectAsState()

    var sectionDataLoaded by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = sectionDataLoaded) {
        if (!sectionDataLoaded) {
            virtualTourGuideViewModel.getSlideSectionData(destination = selectedDestination!!)
            sectionDataLoaded = true
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TravelTopBarV2(
                "Destination Details",
                onBackPress = { navigateUp?.invoke() }
            )
        }
    ) {

        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
        ) {

            selectedDestination?.let {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    item {
                        DetailImageSlider(
                            images = it.images.mapIndexed { id, image ->
                                DetailImageItem(id.toString(), image, isSelected = id == 0)
                            },
                            onImageSelect = {
//                            detailsViewModel.onImageSelected(it)
                            }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        DetailDestinationSection(destination = it)
                        Spacer(modifier = Modifier.height(12.dp))

                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(it.tags) { tag ->
                                TravelItemChip(name = tag)
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    showCompleteOverview.value = !showCompleteOverview.value
                                },
                            text = it.overview,
                            style = TextStyle.Default.copy(
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 16.sp
                            ),
                            maxLines = if (showCompleteOverview.value) 8 else 3,
                            overflow = TextOverflow.Ellipsis
                        )


                    }

                    items(uiState.value.slideSections) {slideSectionItem->
                        DestinationSlideSection(title = slideSectionItem.title, icon = slideSectionItem.icon) {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                slideSectionItem.items.forEach { 
                                    SlideItem(slideItem = it)
                                }
                            }
                        }
                    }
                }
            }

        }
    }


}


@Preview
@Composable
fun DetailScreenPreview(

) {
    MaterialTheme() {
//        DestinationDetailScreen(
//        )
    }

}