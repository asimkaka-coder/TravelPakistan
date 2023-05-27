package com.tp.travelpakistan.ui.auth.data

import com.tp.travelpakistan.ui.Resource
import com.tp.travelpakistan.ui.auth.data.models.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImplementation @Inject constructor(
    private val authApiService: AuthApiService
):AuthRepository {

    override suspend fun signUpUser(user: SignUpRequestBody): Resource<SignUpResponse> {
        try {
            val response = authApiService.signUpUser(user)
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

    override suspend fun signInUser(user: SignInRequestBody): Resource<SignInResponse>{
        try {
            val response = authApiService.signInUser(user)
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
