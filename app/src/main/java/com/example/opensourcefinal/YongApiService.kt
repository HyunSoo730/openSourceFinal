package com.example.opensourcefinal

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YongApiService {
    @GET(BuildConfig.ENDPOINT_GET_STORE_STATUS_YONG)
    fun getInfo(
        @Query("page")Page:Int,
        @Query("perPage")PerPage:Int,
        @Query("serviceKey")ServiceKey:String = BuildConfig.API_KEY_YONG
    ): Call<YongApiData>
}