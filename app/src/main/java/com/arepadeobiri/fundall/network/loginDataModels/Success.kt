package com.arepadeobiri.fundall.network.loginDataModels


import com.google.gson.annotations.SerializedName


data class Success(

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("status")
	val status: String? = null
)