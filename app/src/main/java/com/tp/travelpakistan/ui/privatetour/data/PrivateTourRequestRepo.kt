package com.tp.travelpakistan.ui.privatetour.data

import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.privatetour.data.model.PrivateTourRequestBody
import com.tp.travelpakistan.ui.privatetour.data.model.PrivateTourRequestResponse
import com.tp.travelpakistan.ui.profile.privatetour.PrivateToursResponse
import java.io.IOException
import javax.inject.Inject

class PrivateTourRequestRepo @Inject constructor(
    private val privateTourRequestApiService: PrivateTourRequestApiService
) {

    suspend fun postPrivateTourRequest(privateTourRequestBody: PrivateTourRequestBody): Resource<PrivateTourRequestResponse> {
        try {
            val response = privateTourRequestApiService.postTourRequest(privateTourRequestBody)
            return if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Check your Network! ${response.message()}")
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

    suspend fun getAllPrivateTourRequests(userId:String): Resource<PrivateToursResponse> {
        try {
            val response = privateTourRequestApiService.getAllPrivateTourRequest(userId)
            return if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Check your Network! ${response.message()}")
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