package com.arepadeobiri.fundall.network.avatarDataModels


import com.google.gson.annotations.SerializedName


data class AvatarResponse(

    @field:SerializedName("success")
    val success: Success? = null,

    @field:SerializedName("error")
    val error: Error? = null
)