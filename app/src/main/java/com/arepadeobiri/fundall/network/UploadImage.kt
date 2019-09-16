package com.arepadeobiri.fundall.network

import com.google.gson.annotations.SerializedName
import java.io.File

data class UploadImage (
    @field:SerializedName("avatar")
    val avatar: String? = null,

    @field:SerializedName("file")
    val file: File? = null
)