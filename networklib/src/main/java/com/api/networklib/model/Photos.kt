package com.api.networklib.model

import com.google.gson.annotations.SerializedName


data class Photos (

  @SerializedName("url"         ) var url         : String? = null,
  @SerializedName("title"       ) var title       : String? = null,
  @SerializedName("user"        ) var user        : Int?    = null,
  @SerializedName("description" ) var description : String? = null,
  @SerializedName("id"          ) var id          : Int?    = null

)