package com.example.atletico.ui.schedule

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

//https://livescore-api.com/
private const val BASE_URL = "https://heisenbug-la-liga-live-scores-v1.p.rapidapi.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ResultApiService {
    //@Headers("X-Auth-Token: 0e32a9bf81174796b9309f4302f9fdcf")
     @Headers("X-RapidAPI-Key: b00f4bb156msh43f2a2457d9585ap1fdcd3jsn9af217826811")
//        "X-RapidAPI-Host: heisenbug-la-liga-live-scores-v1.p.rapidapi.com")
    @GET("api/laliga/table")
    suspend fun getStandings(
//        @Query("X-Auth-Token") key: String = "0e32a9bf81174796b9309f4302f9fdcf",
//        @Query("X-RapidAPI-Key") X-RapidAPI-Key: String = "b00f4bb156msh43f2a2457d9585ap1fdcd3jsn9af217826811"
       // @Query("X-RapidAPI-Host")X-RapidAPI-Host: String = "heisenbug-la-liga-live-scores-v1.p.rapidapi.com"
    ): Response<Table>
}

object ResultApi {
    val retrofitService: ResultApiService by lazy { retrofit.create(ResultApiService::class.java) }
}