package com.tp.travelpakistan.ui.auth.di

import com.tp.travelpakistan.ui.auth.data.AuthApiService
import com.tp.travelpakistan.ui.auth.data.AuthRepository
import com.tp.travelpakistan.ui.auth.data.AuthRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
internal object AuthModule {


    @Provides
    fun provideAuthRepository(authApiService: AuthApiService):AuthRepository = AuthRepositoryImplementation(authApiService)
}