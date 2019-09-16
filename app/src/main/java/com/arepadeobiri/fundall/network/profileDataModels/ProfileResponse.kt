package com.arepadeobiri.fundall.network.profileDataModels


import com.google.gson.annotations.SerializedName


data class ProfileResponse(

	@field:SerializedName("success")
	val success: Success? = null
)