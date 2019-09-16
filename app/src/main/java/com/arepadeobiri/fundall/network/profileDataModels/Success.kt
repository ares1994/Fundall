package com.arepadeobiri.fundall.network.profileDataModels


import com.google.gson.annotations.SerializedName


data class Success(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("status")
	val status: String? = null
)