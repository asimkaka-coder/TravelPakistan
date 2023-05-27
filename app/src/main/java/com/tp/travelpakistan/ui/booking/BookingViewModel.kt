package com.tp.travelpakistan.ui.booking

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tp.travelpakistan.ui.booking.data.models.BookingUiModel
import com.tp.travelpakistan.ui.booking.data.models.SummaryUiModel
import com.tp.travelpakistan.ui.home.ui.model.TourPackage
import com.tp.travelpakistan.ui.home.ui.model.listOfTours
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(

) : ViewModel() {


    private val _bookingUiState: MutableStateFlow<BookingUiModel> = MutableStateFlow(
        BookingUiModel(
        "",1,0,"Islamabad", listOf(), SummaryUiModel(
            1,0,0.0,0.0,0.0,""
        )
    )
    )
    val bookingUiState: StateFlow<BookingUiModel?> = _bookingUiState

    val tourPackageState:MutableState<TourPackage> = mutableStateOf(listOfTours[0])


    fun initUiState(tourPackage: TourPackage) {
        tourPackageState.value = tourPackage
        _bookingUiState.update {
            it.copy(
                bookingDate = "",
                adultPassengers = 1,
                childPassengers = 0,
                pickupLocation = "Islamabad",
                pickupLocations = listOf("Lahore","Islamabad","Peshawar","Karachi"),
                summaryUiModel = SummaryUiModel(
                    adultPackages = it.adultPassengers,
                    childPackage = it.childPassengers,
                    standardTotalCharge = tourPackage.price,
                    childTotalCharge = 0.0,
                    conveyanceCharge = 0.0,
                    pickup = it.pickupLocation,
                )
            )
        }
    }


    fun onPickupSelect(location:String){
        _bookingUiState.update {
            it.copy(
                pickupLocation = location,
                summaryUiModel = it.summaryUiModel.copy(
                    pickup = location
                )
            )
        }
    }


    fun onDateSelect(date:String){
        _bookingUiState.update {
            it.copy(bookingDate = date)
        }
    }


    fun onNumberOfTravellersChange(travllers:Int){
        _bookingUiState.update {
            it.copy(
                adultPassengers = travllers,
                summaryUiModel = it.summaryUiModel.copy(
                    adultPackages = travllers,
                    standardTotalCharge = tourPackageState.value.price*travllers,
                )
            )
        }
    }

    fun onNumberOfChildTravellersChange(child:Int){
        _bookingUiState.update {
            it.copy(
                childPassengers = child,
                summaryUiModel = it.summaryUiModel.copy(
                    childPackage = child,
                    childTotalCharge = tourPackageState.value.priceForChildren*child,
                )
            )
        }
    }


}