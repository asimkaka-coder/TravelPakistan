package com.tp.travelpakistan.ui.virtualtourguide.data.alltours

import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.home.data.toppicks.model.TopToursResponse
import com.tp.travelpakistan.ui.virtualtourguide.data.alltours.AllDestinationsApiService
import com.tp.travelpakistan.ui.virtualtourguide.data.alltours.models.AllDestinationsResponse
import java.io.IOException
import javax.inject.Inject

class AllDestinationsRepository @Inject constructor(
    private val allDestinationsApiService: AllDestinationsApiService
) {

    suspend fun getAllDestinations(): Resource<AllDestinationsResponse> {
        try {
            val response = allDestinationsApiService.getAllDestinations()
            return if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Check your Network!", AllDestinationsResponse("No Data", listOf()))
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