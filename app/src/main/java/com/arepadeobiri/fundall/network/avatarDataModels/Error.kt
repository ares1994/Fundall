package com.arepadeobiri.fundall.network.avatarDataModels


import com.google.gson.annotations.SerializedName


data class Error(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)