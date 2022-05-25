package com.example.opensourcefinal

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object YongRetrofitObject {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL_STORE_YONG)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(): YongApiService {
        return getRetrofit().create(YongApiService::class.java)
    }
}