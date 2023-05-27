package com.tp.travelpakistan.ui.home.data.explore

import com.tp.travelpakistan.ui.home.data.toppicks.model.TopToursResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExploreApiService {


    @GET("search-tours")
    suspend fun searchTour(
        @Query("destination") destination:String ,
        @Query("pickup") pickup:String ,
        @Query("days") days:Int,
        @Query("people") people:Int,
        ):Response<TopToursResponse>
}
