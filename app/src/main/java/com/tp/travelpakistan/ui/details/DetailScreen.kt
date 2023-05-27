package com.tp.travelpakistan.ui.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
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
import com.tp.travelpakistan.ui.details.components.*
import com.tp.travelpakistan.ui.details.data.cancellationPolicies
import com.tp.travelpakistan.ui.details.data.childPolicies
import com.tp.travelpakistan.ui.details.data.refundPolicies
import com.tp.travelpakistan.ui.details.model.*
import com.tp.travelpakistan.ui.home.ui.components.TravelTopBarV2
import com.tp.travelpakistan.ui.home.ui.model.TourPackage
import com.tp.travelpakistan.ui.home.ui.model.listOfTours
import com.tp.travelpakistan.ui.profile.TabRowItem
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    images: List<DetailImageItem>,
    tabItems: List<DetailTabItem>,
    onImageSelect: (DetailImageItem) -> Unit,
    onTabClick: (DetailTabItem) -> Unit,
    goToBooking: ((TourPackage) -> Unit)? = null,
    goToOrganizerDetails: (String) -> Unit = {},
    navigateUp: (() -> Unit)? = null,
    tourId: String = "64540e3b569cfef192fb4866",
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    var showCompleteOverview = remember { mutableStateOf(false) }
    var openBottomSheet by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    val snackbarHostState = remember { SnackbarHostState() }
    val bottomSheetShowItem = remember {
        mutableStateOf(DetailTabType.NONE)
    }
    var dataLoaded by rememberSaveable {
        mutableStateOf(false)
    }


    LaunchedEffect(key1 = dataLoaded) {
        if (!dataLoaded) {
            detailsViewModel.fetchTourDetails(tourId)
            dataLoaded = true
        }
    }

    val uiState = detailsViewModel.detailsUiState.collectAsState()

    uiState.value.data?.let {
        if (openBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { openBottomSheet = false },
                sheetState = bottomSheetState,
            ) {

                when (bottomSheetShowItem.value) {
                    DetailTabType.NONE -> {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(), horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                // Note: If you provide logic outside of onDismissRequest to remove the sheet,
                                // you must additionally handle intended state cleanup, if any.
                                onClick = {
                                    scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                                        if (!bottomSheetState.isVisible) {
                                            openBottomSheet = false
                                        }
                                    }
                                }
                            ) {
                                Text("Content Soon!")
                            }
                        }
                    }
                    DetailTabType.OVERVIEW -> {
                        DetailsTabSection(title = "Description", icon = R.drawable.ic_description) {
                            Text(
                                text = it.description,
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                    DetailTabType.DETAILS -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            DetailsTabSection(title = "Location", icon = R.drawable.ic_location) {
                                LocationSection(pickup = it.departureLocation[0], routes = it.route)
                            }

                            DetailsTabSection(title = "Timing", icon = R.drawable.ic_time) {
                                TimingSection(
                                    duration = "${it.durationDays} Days",
                                    departure = it.departureTime
                                )
                            }

                            DetailsTabSection(title = "Travel Tips", icon = R.drawable.ic_tips) {
                                TravelTipsSections(tips = it.tips)
                            }

                        }

                    }
                    DetailTabType.REVIEWS -> {
                        goToOrganizerDetails(it.agencyId!!)
                        openBottomSheet = false
                    }
                    DetailTabType.OPTIONS -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            DetailsTabSection(
                                title = "Inclusions Exclusions",
                                icon = R.drawable.ic_incl_excl
                            ) {
                                InclusionExclusionSection(
                                    inclusions = it.inclusions,
                                    exclusions = it.exclusions
                                )
                            }
                        }
                    }
                    DetailTabType.POLICY -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                        ) {
                            DetailsTabSection(
                                title = "Privacy Policies",
                                icon = R.drawable.ic_privacy
                            ) {
                                PrivacyPolicySection(
                                    cancellations = cancellationPolicies,
                                    refunds = refundPolicies,
                                    chilsPolicies = childPolicies
                                )
                            }

                        }
                    }
                }

            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TravelTopBarV2(
                "Details",
                onBackPress = { navigateUp?.invoke() }
            )
        },
        bottomBar = {
            uiState.value.data?.let {
                Button(
                    onClick = {
                        goToBooking?.invoke(it.toTourPackage())
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        .height(54.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "BOOK NOW", style = TextStyle(fontSize = 18.sp))
                }
            }
        }
    ) {

        Surface(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
        ) {


            if (uiState.value.isLoadingData) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            if (uiState.value.isError) {
                LaunchedEffect(key1 = snackbarHostState) { // using the `coroutineScope` to `launch` showing the snackbar
                    // taking the `snackbarHostState` from the attached `scaffoldState`
                    val snackbarResult = snackbarHostState.showSnackbar(
                        message = "Connectivity Issues",
                        actionLabel = "Retry"
                    )
                    when (snackbarResult) {
                        SnackbarResult.Dismissed -> Log.d("SnackbarDemo", "Dismissed")
                        SnackbarResult.ActionPerformed -> {
                            detailsViewModel.fetchTourDetails(tourId)
                        }
                    }
                }
            }

            uiState.value.data?.let {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    DetailImageSlider(
                        images = detailsViewModel.imagesList.value,
                        onImageSelect = {
                            detailsViewModel.onImageSelected(it)
                        }
                    )

                    DetailTourSection(
                        tourItem = it.toTourPackage()
                    ) {
                        goToOrganizerDetails(it.agencyId!!)
                    }



                    DetailTabRow(
                        items = tabItems,
                        onTabClick = {
                            onTabClick(it)
                            bottomSheetShowItem.value = it.type
                            openBottomSheet = !openBottomSheet
                        }
                    )


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
            }

        }
    }


}


@Preview
@Composable
fun DetailScreenPreview(

) {
    MaterialTheme() {
        DetailScreen(
            images = images,
            tabItems = tabItems,
            onImageSelect = {}, onTabClick = {}
        )
    }

}