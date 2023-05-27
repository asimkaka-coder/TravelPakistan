package com.tp.travelpakistan.ui.virtualtourguide.data.alltours

import com.tp.travelpakistan.ui.virtualtourguide.data.alltours.models.AllDestinationsResponse
import retrofit2.Response
import retrofit2.http.GET

interface AllDestinationsApiService {

    @GET("all-destinations")
    suspend fun getAllDestinations():Response<AllDestinationsResponse>


}
