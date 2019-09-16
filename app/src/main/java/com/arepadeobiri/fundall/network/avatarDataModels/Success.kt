package com.arepadeobiri.fundall.network.avatarDataModels


import com.google.gson.annotations.SerializedName


data class Success(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)