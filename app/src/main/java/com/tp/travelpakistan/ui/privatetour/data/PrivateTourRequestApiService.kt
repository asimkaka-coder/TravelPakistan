package com.tp.travelpakistan.ui.privatetour.data

import com.tp.travelpakistan.ui.privatetour.data.model.PrivateTourRequestBody
import com.tp.travelpakistan.ui.privatetour.data.model.PrivateTourRequestResponse
import com.tp.travelpakistan.ui.profile.privatetour.PrivateToursResponse
import retrofit2.Response
import retrofit2.http.*

interface PrivateTourRequestApiService {

    @Headers("Content-Type: application/json")
    @POST("create-private-tour")
    suspend fun postTourRequest(
        @Body privateTourRequestBody: PrivateTourRequestBody
    ): Response<PrivateTourRequestResponse>


    @GET("private-tours/{userId}")
    suspend fun getAllPrivateTourRequest(@Path("userId") userID: String): Response<PrivateToursResponse>
}
