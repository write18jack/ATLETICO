package com.example.atletico.ui.schedule

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://livescore-api.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ResultApiService {
    @GET("api-client/leagues/table.json?competition_id=3")
    suspend fun getStandings(
        @Query("key") key: String = "wBoHE2O3jFrat3TC",
        @Query("secret")secret: String = "HxDvakhOf355OBqlcP2QRRUbwll0peC6"
    ): Response<Standings>
}

object ResultApi {
    val retrofitService: ResultApiService by lazy { retrofit.create(ResultApiService::class.java) }
}