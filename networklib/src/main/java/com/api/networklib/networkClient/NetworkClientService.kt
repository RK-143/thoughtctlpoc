package com.api.networklib.networkClient

import com.api.networklib.apiInterface.CloudAPIInterface
import com.api.networklib.uitls.NetworkConstant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClientService {
    var retrofit: Retrofit? = null

    var client = OkHttpClient.Builder().build()
    fun getRetroClient(): CloudAPIInterface? {
        retrofit = Retrofit.Builder().baseUrl(NetworkConstant.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return createAPI()
    }

    fun createAPI(): CloudAPIInterface? {
        return retrofit?.create(CloudAPIInterface::class.java)
    }
}