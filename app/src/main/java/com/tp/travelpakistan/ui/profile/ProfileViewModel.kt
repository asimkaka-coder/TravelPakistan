package com.tp.travelpakistan.ui.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.auth.data.UserSession
import com.tp.travelpakistan.ui.payment.data.PurchaseTourRepository
import com.tp.travelpakistan.ui.payment.data.models.CancelTourRequestBody
import com.tp.travelpakistan.ui.payment.data.models.PurchasedTourTicket
import com.tp.travelpakistan.ui.payment.ui.components.PurchasedTicket
import com.tp.travelpakistan.ui.payment.ui.components.TicketItem
import com.tp.travelpakistan.ui.privatetour.data.PrivateTourRequestRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userSession: UserSession,
    private val purchaseTourRepository: PurchaseTourRepository,
    private val privateTourRequestRepo: PrivateTourRequestRepo
) : ViewModel() {

    val userInfo = mutableStateOf(userSession.userInfo)
    private val currentTickets = mutableStateOf(listOf<PurchasedTourTicket>())
    private val _profileUiState: MutableStateFlow<ProfileUiState> = MutableStateFlow(
        ProfileUiState(
            isLoadingData = false, isError = false,
            data = listOf()
        )
    )
    val profileUiState = _profileUiState

    init {
//        userInfo.value?.let {
//            getPurchasedTikets(it._id)
//        }
    }

    fun cancelBooking(bookingNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val ticketToBeRemoved = currentTickets.value.first {
                it._id == bookingNumber
            }

            val cancelTourRequestBody = CancelTourRequestBody(
                purchasedBy = ticketToBeRemoved.purchasedBy,
                tour = ticketToBeRemoved.tour,
                pickup = ticketToBeRemoved.pickup,
                amount = ticketToBeRemoved.amount
            )

            when (val response = purchaseTourRepository.cancelTourBooking(cancelTourRequestBody)) {
                is Resource.Error -> {
                    Log.d("jeje", "Error: ${response.message}")
                }
                is Resource.Loading -> {}
                is Resource.Success -> {
                    Log.d(
                        "jeje",
                        "Message: ${response.data}\n Data: ${response.data}"
                    )
                    userInfo.value?.let {
                        getPurchasedTikets(it._id)
                    }
                }
            }
        }
    }

     fun getPurchasedTikets(
        Id: String? = userInfo.value?._id
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _profileUiState.update {
                it.copy(isLoadingData = true)
            }
            val response = purchaseTourRepository.fetchPurchasedTours(userId = Id!!)
            val privateRequestsResponse =
                privateTourRequestRepo.getAllPrivateTourRequests(userId = Id)
            when (response) {
                is Resource.Error -> {
                    _profileUiState.update {
                        it.copy(
                            isLoadingData = false,
                            isError = true
                        )
                    }
                    Log.d("jeje", "Error: ${response.message}")
                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    Log.d(
                        "jeje",
                        "Message: ${response.data?.message}\n Data: ${response.data}"
                    )
                    currentTickets.value = response.data?.tours ?: listOf()
                    val tickets = response.data?.tours?.map {
                        PurchasedTicket(
                            username = userInfo.value!!.username,
                            status = "Booked",
                            ticketItems = listOf(
                                TicketItem(
                                    firstItem = Pair("Tour ID", it.tour._id),
                                    secondItem = Pair(
                                        "Travellers",
                                        "${it.travellers.adults} Adults & ${it.travellers.children} children"
                                    )
                                ),
                                TicketItem(
                                    firstItem = Pair("Pickup", it.pickup),
                                    secondItem = Pair(
                                        "Amount",
                                        "PKR ${it.amount}"
                                    )
                                )
                            ),
                            bookingNumber = it._id
                        )
                    } ?: listOf()

                    if (privateRequestsResponse.data != null) {
                        _profileUiState.update {
                            it.copy(
                                requestsData = privateRequestsResponse.data.privateTours
                            )
                        }
                    }

                    _profileUiState.update {
                        it.copy(
                            isLoadingData = false,
                            isError = false,
                            data = tickets,
                        )
                    }
                }
            }
        }
    }
}