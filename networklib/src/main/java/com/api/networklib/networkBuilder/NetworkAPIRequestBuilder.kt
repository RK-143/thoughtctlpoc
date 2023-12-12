package com.api.networklib.networkBuilder

import com.api.networklib.networkClient.NetworkClientService

import com.api.networklib.repository.responseListener.IOnGetResponseListener
import com.api.networklib.uitls.NetworkUtils.getGsonConversion
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

object NetworkAPIRequestBuilder {
lateinit var iOnGetResponseListener: IOnGetResponseListener
    fun getAPICall(requestObject:Any, iOnGetResponseListener: IOnGetResponseListener)
    {
        this.iOnGetResponseListener=iOnGetResponseListener
        val requestBody: RequestBody =
            RequestBody.create(MediaType.parse("application/json"), getGsonConversion(requestObject))

        val serviceCall=NetworkClientService.getRetroClient()?.getImageList()
        serviceCall?.enqueue(object :retrofit2.Callback<ResponseBody?> {
            override fun onResponse(
                call: Call<ResponseBody?>?,
                response: Response<ResponseBody?>?
            ) {
                iOnGetResponseListener.onGetSuccessService(response?.body()?.string())
            }

            override fun onFailure(call: Call<ResponseBody?>?, t: Throwable?) {
                iOnGetResponseListener.onGetSuccessService(t?.message)
            }


        })
    }

}