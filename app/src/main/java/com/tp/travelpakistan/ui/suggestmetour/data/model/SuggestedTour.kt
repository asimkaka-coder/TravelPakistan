package com.tp.travelpakistan.ui.suggestmetour.data.model

import com.tp.travelpakistan.ui.home.data.toppicks.model.Itinerary
import com.tp.travelpakistan.ui.home.data.toppicks.model.Organizer
import com.tp.travelpakistan.ui.home.data.toppicks.model.Price
import com.tp.travelpakistan.ui.home.ui.model.TourPackage

data class SuggestedTour(
    val __v: Int,
    val _id: String,
    val active: Boolean,
    val capacity: Int,
    val createdAt: String,
    val departureDate: String,
    val departureLocation: List<String>,
    val departureTime: String,
    val description: String,
    val destination: String,
    val discount: Int,
    val durationDays: Int,
    val exclusions: List<String>,
    val featured: Boolean,
    val images: List<String>,
    val inclusions: List<String>,
    val itinerary: Itinerary,
    val name: String,
    val organizer: Organizer,
    val overview: String,
    val price: Price,
    val requirements: List<String>,
    val route: List<String>,
    val tags: List<String>,
    val ticketsPurchased: Int,
    val tips: List<String>,
    val updatedAt: String
){
    fun toTourPackage(): TourPackage =
        TourPackage(
            tourId = this._id,
            tourTitle = this.name,
            tourDestination = this.destination,
            duration = this.durationDays,
            maxPeople = this.capacity ,
            price = price.adults.toDouble() ,
            priceForChildren = price.children.toDouble(),
            infoTags = tags,
            images = images
        )
}