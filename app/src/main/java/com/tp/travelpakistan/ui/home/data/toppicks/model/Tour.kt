package com.tp.travelpakistan.ui.home.data.toppicks.model

import com.tp.travelpakistan.ui.home.ui.model.TourPackage

data class Tour(
    val __v: Int,
    val _id: String,
    val active: Boolean,
    val agencyId:String?,
    val agencyName:String?,
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
    val organizer: String,
    val overview: String,
    val price: Price,
    val requirements: List<String>,
    val route: List<String>,
    val tags: List<String>,
    val ticketsPurchased: Int,
    val tips: List<String>,
    val updatedAt: String
){
    fun toTourPackage():TourPackage =
        TourPackage(
            tourId = this._id,
            tourTitle = this.name,
            agencyName = this.agencyName?:"",
            tourDestination = this.destination,
            duration = this.durationDays,
            maxPeople = this.capacity ,
            price = price.adults.toDouble() ,
            priceForChildren = price.children.toDouble(),
            infoTags = tags,
            images = images
        )
}