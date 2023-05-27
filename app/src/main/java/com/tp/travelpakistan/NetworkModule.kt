package com.tp.travelpakistan

import com.tp.travelpakistan.ui.organizer.data.OrganizerApiService
import com.tp.travelpakistan.ui.auth.data.AuthApiService
import com.tp.travelpakistan.ui.details.data.TourDetailsApiService
import com.tp.travelpakistan.ui.virtualtourguide.data.alltours.AllDestinationsApiService
import com.tp.travelpakistan.ui.home.data.explore.ExploreApiService
import com.tp.travelpakistan.ui.home.data.toppicks.TopPicksApiService
import com.tp.travelpakistan.ui.payment.data.PurchaseTourApiService
import com.tp.travelpakistan.ui.privatetour.data.PrivateTourRequestApiService
import com.tp.travelpakistan.ui.suggestmetour.data.SuggestMeTourApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


const val BASE_URL = "https://serverurl.com"

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {



    @Provides
    @Singleton
    fun provideRetrofitClient(
        client: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        )

    @Provides
    @Singleton
    fun provideOkHttp(
        interceptor: HttpLoggingInterceptor,
    ) =
        OkHttpClient.Builder()
            .connectTimeout(15L, TimeUnit.SECONDS)
            .writeTimeout(15L, TimeUnit.SECONDS)
            .readTimeout(15L, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()



    @Provides
    @Singleton
    fun provideAllDestinationApi(retrofit: Retrofit) = retrofit.create(AllDestinationsApiService::class.java)

    @Provides
    @Singleton
    fun provideTopDestinationsApi(retrofit: Retrofit) = retrofit.create(TopPicksApiService::class.java)

    @Provides
    @Singleton
    fun provideTourDetailsApi(retrofit: Retrofit) = retrofit.create(TourDetailsApiService::class.java)

    @Provides
    @Singleton
    fun providePrivateTourRequestApi(retrofit: Retrofit) = retrofit.create(PrivateTourRequestApiService::class.java)

    @Provides
    @Singleton
    fun provideAuthServiceApi(retrofit: Retrofit) = retrofit.create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun provideExploreTourApi(retrofit: Retrofit) = retrofit.create(ExploreApiService::class.java)

    @Provides
    @Singleton
    fun provideSuggestMeTourApi(retrofit: Retrofit) = retrofit.create(SuggestMeTourApiService::class.java)


    @Provides
    @Singleton
    fun provideOrganizerApi(retrofit: Retrofit) = retrofit.create(OrganizerApiService::class.java)

    @Provides
    @Singleton
    fun providePurchaseTourApi(retrofit: Retrofit) = retrofit.create(PurchaseTourApiService::class.java)


}