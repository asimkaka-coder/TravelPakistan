package com.tp.travelpakistan.ui.details.data

import com.tp.travelpakistan.ui.details.data.model.TourDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TourDetailsApiService {

    @GET("search-tours/{Id}")
    suspend fun getTourDetails(
        @Path("Id") tourId:String
    ):Response<TourDetailsResponse>
}
