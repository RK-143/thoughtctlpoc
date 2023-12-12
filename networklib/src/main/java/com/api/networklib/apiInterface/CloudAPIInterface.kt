package com.api.networklib.apiInterface

import okhttp3.Call
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CloudAPIInterface {

    @GET("v1/sample-data/photos?offset=5&limit=10")
    fun getImageList(): retrofit2.Call<ResponseBody?>?
}