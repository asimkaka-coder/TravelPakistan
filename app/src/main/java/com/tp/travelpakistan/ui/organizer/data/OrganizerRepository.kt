package com.tp.travelpakistan.ui.organizer.data

import com.tp.travelpakistan.ui.organizer.data.models.OrganizerResponse
import com.tp.travelpakistan.ui.organizer.data.models.ReviewResponse
import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.home.data.toppicks.model.TopToursResponse
import java.io.IOException
import javax.inject.Inject

class OrganizerRepository @Inject constructor(
    private val organizerApiService: OrganizerApiService
) {

    suspend fun getOrganizerDetails(organizerId:String): Resource<OrganizerResponse> {
        try {
            val response = organizerApiService.getOrganizer(organizerId)
            return if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Check your Network!", null)
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

    suspend fun fetchReviews(organizerId:String): Resource<ReviewResponse> {
        try {
            val response = organizerApiService.fetchReviews(organizerId)
            return if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Check your Network!", null)
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

    suspend fun fetchTours(organizerId:String): Resource<TopToursResponse> {
        try {
            val response = organizerApiService.featchTours(organizerId)
            return if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Check your Network!", null)
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
