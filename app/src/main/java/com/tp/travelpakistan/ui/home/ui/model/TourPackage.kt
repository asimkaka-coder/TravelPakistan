package com.tp.travelpakistan.ui.home.ui.model

import android.os.Parcelable
import com.tp.travelpakistan.ui.home.ui.components.dummayImage
import kotlinx.parcelize.Parcelize

@Parcelize
data class TourPackage(
    val tourId:String,
    val tourTitle:String,
    val tourDestination:String,
    val agencyName:String="",
    val duration:Int,
    val maxPeople:Int,
    val price:Double=0.0,
    val priceForChildren:Double,
    val infoTags:List<String>,
    val images:List<String> = listOf()
):Parcelable {
    val pricePerPersion
        get()= "$price"

    val durationDays
        get() = "$duration Days"

    val capacity
        get() = "$maxPeople People"
}


val listOfTours = listOf<TourPackage>(
    TourPackage(
        tourId = "1",
        tourTitle = "8 Days Tour to Hunza and Kalash Valley",
        tourDestination = "Hunza/Swat Valley",
        duration = 6,
        maxPeople = 43,
        price = 25000.0,
        priceForChildren = 0.0,
        infoTags = listOf("Food","Bonfire","Stay","Lunch"),
        images = listOf(dummayImage, dummayImage, dummayImage, dummayImage)
    )
)