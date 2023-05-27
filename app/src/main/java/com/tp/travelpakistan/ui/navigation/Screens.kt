package com.tp.travelpakistan.ui.navigation

sealed class DestinationScreen(val route:String){
    object SignIn:DestinationScreen("signin")
    object SignUp:DestinationScreen("signup")

    object Home:DestinationScreen("home")
    object Details:DestinationScreen("details")

    object Booking:DestinationScreen("booking")

    object Payment:DestinationScreen("payment")

    object PrivateTourRequest:DestinationScreen("privatetour")

    object SuggestMeTour:DestinationScreen("suggesttour")

    object Organizer:DestinationScreen("organizer")

    object VirtualTourGuide:DestinationScreen("virtualtourguide")

    object DestinationDetails:DestinationScreen("destinationdetails")
}