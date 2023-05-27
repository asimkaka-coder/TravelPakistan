package com.tp.travelpakistan.ui.payment.data

import com.tp.travelpakistan.ui.payment.data.models.CancelTourRequestBody
import com.tp.travelpakistan.ui.payment.data.models.PurchaseTourRequestBody
import com.tp.travelpakistan.ui.payment.data.models.PurchaseTourResponse
import com.tp.travelpakistan.ui.payment.data.models.PurchaseTourTicketResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PurchaseTourApiService {


    @POST("purchase-tour")
    suspend fun purchaseTour(
        @Body purchaseTourRequestBody: PurchaseTourRequestBody
    ): Response<PurchaseTourResponse>


    @POST("cancel-booking")
    suspend fun cancelBooking(
        @Body cancelTourRequestBody: CancelTourRequestBody
    ): Response<ResponseBody>

    @GET("purchased-tours/{userId}")
    suspend fun fetchPurchasedTours(
        @Path("userId") userId: String
    ): Response<PurchaseTourTicketResponse>
}