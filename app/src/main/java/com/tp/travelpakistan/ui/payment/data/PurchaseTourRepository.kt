package com.tp.travelpakistan.ui.payment.data

import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.payment.data.models.CancelTourRequestBody
import com.tp.travelpakistan.ui.payment.data.models.PurchaseTourRequestBody
import com.tp.travelpakistan.ui.payment.data.models.PurchaseTourResponse
import com.tp.travelpakistan.ui.payment.data.models.PurchaseTourTicketResponse
import okhttp3.ResponseBody
import java.io.IOException
import javax.inject.Inject

class PurchaseTourRepository @Inject constructor(
    private val purchaseTourApiService: PurchaseTourApiService
) {

    suspend fun purchaseTour(purchaseTourRequestBody: PurchaseTourRequestBody): Resource<PurchaseTourResponse> {
        try {
            val response = purchaseTourApiService.purchaseTour(purchaseTourRequestBody)
            return if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Check your Network! ${response.message()}")
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    t.printStackTrace()
                    return Resource.Error("Retrofit fucked up!")
                }
            }
        }
        return Resource.Error("Server Error")
    }


    suspend fun fetchPurchasedTours(userId: String): Resource<PurchaseTourTicketResponse> {
        try {
            val response = purchaseTourApiService.fetchPurchasedTours(userId)
            return if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Check your Network! ${response.message()}")
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    t.printStackTrace()
                    return Resource.Error("Retrofit fucked up!")
                }
            }
        }
        return Resource.Error("Server Error")
    }


    suspend fun cancelTourBooking(cancelTourRequestBody: CancelTourRequestBody): Resource<ResponseBody> {
        try {
            val response = purchaseTourApiService.cancelBooking(cancelTourRequestBody)
            return if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Check your Network! ${response.code()} ${response.message()}")
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    t.printStackTrace()
                    return Resource.Error("Retrofit fucked up!")
                }
            }
        }
        return Resource.Error("Server Error")
    }

}