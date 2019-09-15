package com.arepadeobiri.fundall.network.signUpDataModels


import com.arepadeobiri.fundall.network.signUpDataModels.Info
import com.google.gson.annotations.SerializedName


data class Response(

    @field:SerializedName("success")
    val success: Info? = null,

    @field:SerializedName("error")
    val error: Info? = null
)