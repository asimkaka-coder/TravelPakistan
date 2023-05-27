package com.tp.travelpakistan.ui.home.data.explore

import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.home.data.toppicks.model.TopToursResponse
import java.io.IOException
import javax.inject.Inject



class ExploreDestinationsRepository @Inject constructor(
    private val exploreApiService: ExploreApiService
) {

    suspend fun searchTours(
        destination:String,
        pickup:String,
        people:Int,
        days:Int
    ): Resource<TopToursResponse> {
        try {
            val response = exploreApiService.searchTour(
                destination, pickup, days, people
            )
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
