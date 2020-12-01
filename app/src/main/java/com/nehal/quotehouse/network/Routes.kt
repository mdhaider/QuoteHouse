package com.nehal.quotehouse.network

import com.nehal.quotehouse.model.McqResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Routes {

    @GET("quotes")
    suspend fun getPost(@QueryMap options: Map<String, String>) : McqResponse
}