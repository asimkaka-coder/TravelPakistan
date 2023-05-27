package com.tp.travelpakistan.ui.suggestmetour.data

import com.tp.travelpakistan.ui.suggestmetour.data.model.SuggestedTourResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SuggestMeTourApiService {


    @GET("suggest-me-tour")
    suspend fun suggestTour(
        @Query("destination") destination:String ,
        @Query("days") days:Int,
        @Query("budget") budget:Int,
        ):Response<SuggestedTourResponse>
}
