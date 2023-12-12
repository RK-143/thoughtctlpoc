package com.api.networklib.repository

import com.api.networklib.model.CloudImageListResponse
import com.api.networklib.networkBuilder.NetworkAPIRequestBuilder
import com.api.networklib.repository.responseListener.IOnGetImageAPIResponse
import com.api.networklib.repository.responseListener.IOnGetResponseListener
import com.api.networklib.uitls.NetworkUtils

object ImageAPIRequestRepository:IOnGetResponseListener {
lateinit var iOnGetImageAPIResponse:IOnGetImageAPIResponse
    fun getImgeAPIRequest(iOnGetImageAPIResponse:IOnGetImageAPIResponse)
    {
        this.iOnGetImageAPIResponse=iOnGetImageAPIResponse
        NetworkAPIRequestBuilder.getAPICall("",this)
    }

    override fun onGetSuccessService(successRes: String?) {
        iOnGetImageAPIResponse.onGetSuccess(NetworkUtils.getActualGsonResponse(successRes!!,
            CloudImageListResponse() ) as CloudImageListResponse)
    }

    override fun onGetFailureSerivce(errorRes: String?) {
        iOnGetImageAPIResponse.onGetFailure(errorRes!!)
    }
}