package com.example.openappchart.api

import com.example.openappchart.constants.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseUrl{
    fun getInstance(): Retrofit {
        var builder= OkHttpClient.Builder()
        builder.addInterceptor(Interceptor { chain ->
            var request = chain.request().newBuilder().addHeader("x-api-key", AppConstants.header).build();
            chain.proceed(request);
        })
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .baseUrl(AppConstants.URL)
            .build()

    }


}