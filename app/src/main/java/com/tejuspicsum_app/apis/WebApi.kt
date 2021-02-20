package com.tejuspicsum_app.apis

import com.tejuspicsum_app.model.ImageResponse
import retrofit2.Call
import retrofit2.http.GET

interface WebApi {

    @GET("list")
    fun getList(): Call<ArrayList<ImageResponse>>
}