package com.yagubbilalzade.retrofittest.model

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("name") val name: String,
    @SerializedName("surname") val surname: String


)
