package com.tp.travelpakistan.ui.suggestmetour.data

import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.home.data.toppicks.model.TopToursResponse
import com.tp.travelpakistan.ui.suggestmetour.data.model.SuggestedTourResponse
import java.io.IOException
import javax.inject.Inject

class SuggestMeTourRepository @Inject constructor(
    private val suggestMeTourApiService: SuggestMeTourApiService
) {

    suspend fun suggestMeTour(
        destination:String,days:Int,budget:Int
    ): Resource<SuggestedTourResponse> {
        try {
            val response = suggestMeTourApiService.suggestTour(
                destination, days, budget
            )
            return if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Check your Network!", SuggestedTourResponse("No Data", listOf()))
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
