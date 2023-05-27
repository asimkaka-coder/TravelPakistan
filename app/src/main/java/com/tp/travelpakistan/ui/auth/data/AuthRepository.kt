package com.tp.travelpakistan.ui.auth.data

import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.auth.data.models.*

interface AuthRepository {

    suspend fun signUpUser(user: SignUpRequestBody):Resource<SignUpResponse>

    suspend fun signInUser(user: SignInRequestBody):Resource<SignInResponse>
}