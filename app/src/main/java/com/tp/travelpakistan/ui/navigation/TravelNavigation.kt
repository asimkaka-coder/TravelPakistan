package com.tp.travelpakistan.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tp.travelpakistan.ui.organizer.ui.OrganizerScreen
import com.tp.travelpakistan.ui.TravelPakistan
import com.tp.travelpakistan.ui.auth.ui.SignInScreen
import com.tp.travelpakistan.ui.auth.ui.SignUpScreen
import com.tp.travelpakistan.ui.booking.BookingScreen
import com.tp.travelpakistan.ui.booking.data.models.BookingDataForPayment
import com.tp.travelpakistan.ui.details.DetailScreen
import com.tp.travelpakistan.ui.details.model.images
import com.tp.travelpakistan.ui.details.model.tabItems
import com.tp.travelpakistan.ui.home.ui.model.TourPackage
import com.tp.travelpakistan.ui.navigation.DestinationScreen.*
import com.tp.travelpakistan.ui.payment.PaymentScreen
import com.tp.travelpakistan.ui.privatetour.ui.PrivateTourScreen
import com.tp.travelpakistan.ui.suggestmetour.SuggestMeTourScreen
import com.tp.travelpakistan.ui.virtualtourguide.data.alltours.models.Destination
import com.tp.travelpakistan.ui.virtualtourguide.ui.DestinationDetailScreen
import com.tp.travelpakistan.ui.virtualtourguide.ui.VirtualTourGuideScreen

@ExperimentalMaterial3Api
@Composable
fun TravelNavigation(
    navController: NavHostController,
    startDestinationScreen: DestinationScreen,
    onToggleDark: (() -> Unit)? = null
) {


    NavHost(navController = navController, startDestination = startDestinationScreen.route) {
        composable(
            SignIn.route
        ) {
            SignInScreen(
                goToSignUp = {
                    navController.navigate(SignUp.route) {
                    }
                },
                goToHome = {
                    navController.navigate(Home.route) {
                        popUpTo(SignIn.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            SignUp.route
        ) {
            SignUpScreen(
                goToSignIn = { navController.navigateUp() },
                onRegisterSuccess = {
                    navController.navigate(Home.route) {
                        popUpTo(SignIn.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Home.route) {
            TravelPakistan(
                onLogoutSession = {
                    navController.navigate(SignIn.route) {
                        popUpTo(Home.route) {
                            inclusive = true
                        }
                    }
                },
                onToggleTheme = { onToggleDark?.invoke() },
                GoToPrivateRequest = { navController.navigate(PrivateTourRequest.route) },
                GoToVirtualTourGuide = { navController.navigate(VirtualTourGuide.route) },
                GoToSuggestMeTour = { navController.navigate(SuggestMeTour.route) }
            ) {
                navController.navigate("${Details.route}/$it")
            }
        }

        composable("${Details.route}/{tourId}") {
            val tourId = it.arguments?.getString("tourId") ?: ""
            DetailScreen(
                images,
                tabItems,
                {
                }, {},
                goToBooking = { tourPackage ->
                    navController.currentBackStackEntry?.arguments?.putParcelable(
                        "tourPackage",
                        tourPackage
                    )
                    navController.navigate(Booking.route)
                },
                goToOrganizerDetails = { orgId ->
                    navController.navigate(Organizer.route + "/${orgId}")
                },
                navigateUp = {
                    navController.navigateUp()
                },
                tourId = tourId
            )
        }

        composable(Booking.route) {
            val tourPackage =
                navController.previousBackStackEntry?.arguments?.getParcelable<TourPackage>("tourPackage")
            BookingScreen(
                tourPackage = tourPackage,
                navigateUp = { navController.navigateUp() },
                goToPayment = { bookingData ->
                    navController.currentBackStackEntry?.arguments?.putParcelable(
                        "bookingData",
                        bookingData
                    )
                    navController.navigate(Payment.route)
                })
        }

        composable(Payment.route) {
            val bookingData =
                navController.previousBackStackEntry?.arguments?.getParcelable<BookingDataForPayment>(
                    "bookingData"
                )
            PaymentScreen(
                bookingDataForPayment = bookingData,
                onPaymentSuccess = {
                    navController.navigate(Home.route) {
                        popUpTo(Home.route)
                        launchSingleTop = true
                    }
                }
            ) {
                navController.navigateUp()
            }
        }

        composable(PrivateTourRequest.route) {
            PrivateTourScreen() {
                navController.navigateUp()
            }
        }

        composable(SuggestMeTour.route) {
            SuggestMeTourScreen(
                GoToDetails = { navController.navigate(Details.route + "/$it") },
                navigateUp = { navController.navigateUp() }
            )
        }

        composable(Organizer.route + "/{organizerId}") {
            val organizerId = it.arguments?.getString("organizerId") ?: ""
            OrganizerScreen(organizerId = organizerId,
                navigateUp = {
                    navController.navigateUp()
                }
            ) {
                navController.navigate("${Details.route}/$it")
            }
        }

        composable(VirtualTourGuide.route) {
            VirtualTourGuideScreen(
                navigateUp = {
                    navController.navigateUp()
                }
            ){destination->
                navController.currentBackStackEntry?.arguments?.putParcelable(
                    "destination",
                    destination
                )
                navController.navigate(DestinationDetails.route)
            }
        }
        
        composable(DestinationDetails.route){
            val destination =
                navController.previousBackStackEntry?.arguments?.getParcelable<Destination>(
                    "destination"
                )
            DestinationDetailScreen(selectedDestination = destination, navigateUp = {
                navController.navigateUp()
            })
        }


    }
}