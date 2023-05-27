package com.tp.travelpakistan.ui.organizer.data

import com.tp.travelpakistan.ui.home.data.toppicks.model.TopToursResponse
import com.tp.travelpakistan.ui.organizer.data.models.OrganizerResponse
import com.tp.travelpakistan.ui.organizer.data.models.ReviewResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OrganizerApiService {



    @GET("organizers/{id}")
    suspend fun getOrganizer(
        @Path("id") organizerId:String
    ):Response<OrganizerResponse>

    @GET("reviews/{id}")
    suspend fun fetchReviews(
        @Path("id") organizerId:String
    ):Response<ReviewResponse>

    @GET("tours/{id}")
    suspend fun featchTours(
        @Path("id") organizerId:String
    ):Response<TopToursResponse>



}