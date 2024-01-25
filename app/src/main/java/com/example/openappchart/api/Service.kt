package com.example.openappchart.api

import com.example.openappchart.OpenInModel.TopLinks
import com.example.openappchart.constants.AppConstants


import retrofit2.Response
import retrofit2.http.*

interface Service {
    @GET("v1/dashboardNew")
    @Headers("Authorization: Bearer ${AppConstants.header}")
    suspend fun getLink(): Response<TopLinks>

}