package com.api.networklib.repository.responseListener

interface IOnGetResponseListener {
    fun onGetSuccessService(errorRes: String?)
    fun onGetFailureSerivce(errorRes:String?)
}