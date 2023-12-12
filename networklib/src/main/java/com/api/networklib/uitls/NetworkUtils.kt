package com.api.networklib.uitls

import com.google.gson.Gson
import java.nio.charset.StandardCharsets

object NetworkUtils {

    fun getGsonConversion(requestObject: Any):ByteArray?
    {
            return Gson().toJson(
                requestObject
            ).toByteArray(StandardCharsets.UTF_8)
    }

    fun getActualGsonResponse(response:String,any: Any):Any
    {
        return Gson().fromJson(response,any::class.java)
    }
}