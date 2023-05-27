package com.tp.travelpakistan.ui.home.data.toppicks

import com.tp.travelpakistan.ui.home.data.toppicks.model.TopToursResponse
import retrofit2.Response
import retrofit2.http.GET

interface TopPicksApiService {

    @GET("top-tours")
    suspend fun getTopTours():Response<TopToursResponse>

}
