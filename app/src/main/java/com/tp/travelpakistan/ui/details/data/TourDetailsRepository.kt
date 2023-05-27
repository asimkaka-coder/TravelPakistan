package com.tp.travelpakistan.ui.details.data

import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.details.data.model.TourDetailsResponse
import java.io.IOException
import javax.inject.Inject

class TourDetailsRepository @Inject constructor(
    private val tourDetailsApiService: TourDetailsApiService
) {

    suspend fun getTourDetails(tourId:String): Resource<TourDetailsResponse> {
        try {
            val response = tourDetailsApiService.getTourDetails(tourId)
            return if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Check your Network!")
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

val cancellationPolicies = listOf<String>(
    "The amount will be deducted for non-refundable advance paid for hotels and transportation services",
    "Any suck cost not mentioned above will be deducted",
    "The refund amount will be processed in 7-10 Days"
)

val refundPolicies = listOf<String>("Please refer to \"Cancellation Policy\" section")

val childPolicies = listOf<String>(
    "Children below 2 years count as infants",
    "Children between 3 to 8 count as Kids",
    "Children above 8 are considered adults"
)