package com.arepadeobiri.fundall.network.loginDataModels


import com.google.gson.annotations.SerializedName


data class LoginResponse(

    @field:SerializedName("success")
    val success: Success? = null,

    @field:SerializedName("error")
    val error: Error? = null
)