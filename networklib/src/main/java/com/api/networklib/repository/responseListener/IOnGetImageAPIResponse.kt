package com.api.networklib.repository.responseListener

import com.api.networklib.model.CloudImageListResponse

interface IOnGetImageAPIResponse {

    fun onGetSuccess(objCloudResponse: CloudImageListResponse)
    fun onGetFailure(errorRes:String)
}