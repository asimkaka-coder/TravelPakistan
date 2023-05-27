package com.tp.travelpakistan.ui.booking.data.models

import android.os.Parcelable
import com.tp.travelpakistan.ui.home.ui.model.TourPackage
import kotlinx.parcelize.Parcelize

data class BookingUiModel(
    val bookingDate:String,
    val adultPassengers:Int,
    val childPassengers:Int,
    val pickupLocation:String,
    val pickupLocations:List<String>,
    val summaryUiModel: SummaryUiModel,
)


@Parcelize
data class SummaryUiModel(
    val adultPackages:Int,
    val childPackage:Int,
    val standardTotalCharge:Double,
    val childTotalCharge:Double,
    val conveyanceCharge:Double,
    val pickup:String
):Parcelable{
    val subTotal
        get() = standardTotalCharge+childTotalCharge
}


@Parcelize
data class BookingDataForPayment(
    val tourPackage: TourPackage,
    val summaryUiModel: SummaryUiModel
):Parcelable