package com.tp.travelpakistan.ui.auth.data

import com.tp.travelpakistan.ui.auth.data.models.SignInRequestBody
import com.tp.travelpakistan.ui.auth.data.models.SignInResponse
import com.tp.travelpakistan.ui.auth.data.models.SignUpRequestBody
import com.tp.travelpakistan.ui.auth.data.models.SignUpResponse
import retrofit2.Response
import retrofit2.http.*

interface AuthApiService {

    @Headers("Content-Type: application/json")
    @POST("sign-in")
    suspend fun signInUser(
        @Body signInRequestBody: SignInRequestBody
    ):Response<SignInResponse>

    @Headers("Content-Type: application/json")
    @POST("sign-up")
    suspend fun signUpUser(
        @Body signUpRequestBody: SignUpRequestBody
    ):Response<SignUpResponse>
}
