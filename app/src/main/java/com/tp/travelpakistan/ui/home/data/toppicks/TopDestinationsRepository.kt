package com.tp.travelpakistan.ui.home.data.toppicks

import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.home.data.toppicks.model.TopToursResponse
import java.io.IOException
import javax.inject.Inject

class TopDestinationsRepository @Inject constructor(
    private val topDestinationsApiService: TopPicksApiService
) {

    suspend fun getTopDestinations(): Resource<TopToursResponse> {
        try {
            val response = topDestinationsApiService.getTopTours()
            return if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Check your Network!", TopToursResponse("No Data", listOf()))
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
